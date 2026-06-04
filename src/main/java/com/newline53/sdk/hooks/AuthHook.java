package com.newline53.sdk.hooks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newline53.sdk.SDKConfiguration;
import com.newline53.sdk.SecuritySource;
import com.newline53.sdk.utils.Helpers;
import com.newline53.sdk.utils.Hook;
import com.newline53.sdk.utils.ResponseWithBody;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

final class AuthHook {

    private static final String AUTH_OPERATION_ID = "generateAuthToken";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String MOCK_AUTHORIZATION_SCHEME = "custom";
    private static final String X_TRACE_ID_HEADER = "x-trace-id";
    private static final long TOKEN_TTL_SECONDS = 8 * 60 * 60;
    private static final long TOKEN_REFRESH_SKEW_SECONDS = 60;
    private static final AtomicReference<AccessTokenState> ACCESS_TOKEN = new AtomicReference<>();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Object REFRESH_LOCK = new Object();

    private AuthHook() {
        // utility class
    }

    static Hook.BeforeRequest beforeRequest() {
        return (context, request) -> {
            HttpRequest requestWithTrace = withTraceIdHeader(request);

            if (AUTH_OPERATION_ID.equals(context.operationId())) {
                Optional<String> programUid = getSecurityValue(context.securitySource(), "programUid");
                Optional<String> hmacKey = getSecurityValue(context.securitySource(), "hmacKey");

                if (programUid.isPresent() && hmacKey.isPresent()) {
                    String refreshToken = generateRefreshToken(programUid.get(), hmacKey.get());
                    return withAuthorizationHeader(requestWithTrace, refreshToken);
                }
            }

            Optional<String> token = ensureValidAccessToken(context);
            if (token.isEmpty()) {
                return requestWithTrace;
            }
            return withAuthorizationHeader(requestWithTrace, token.get());
        };
    }

    static Hook.AfterSuccess afterSuccess() {
        return (context, response) -> {
            if (!AUTH_OPERATION_ID.equals(context.operationId()) || response.statusCode() != 201) {
                return response;
            }

            byte[] bodyBytes = response.body().readAllBytes();
            extractToken(bodyBytes).ifPresent(AuthHook::cacheToken);

            return new ResponseWithBody<>(response, new ByteArrayInputStream(bodyBytes));
        };
    }

    public static String generateRefreshToken(String programUid, String hmacKey) {
        if (programUid == null || programUid.isBlank()) {
            throw new IllegalArgumentException("programUid cannot be blank");
        }
        if (hmacKey == null || hmacKey.isBlank()) {
            throw new IllegalArgumentException("hmacKey cannot be blank");
        }

        String headerJson = "{\"alg\":\"HS512\",\"typ\":\"JWT\"}";
        String payloadJson = "{\"sub\":\""
            + escapeJson(programUid)
            + "\",\"iat\":"
            + Instant.now().getEpochSecond()
            + "}";

        String encodedHeader = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
        String encodedPayload = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));
        String signingInput = encodedHeader + "." + encodedPayload;

        byte[] signature = hmacSha512(hmacKey, signingInput);
        return signingInput + "." + base64UrlEncode(signature);
    }

    private static HttpRequest withAuthorizationHeader(HttpRequest request, String value) {
        HttpRequest.Builder builder = Helpers.copy(
            request,
            (name, ignoredValue) -> !AUTHORIZATION_HEADER.equalsIgnoreCase(name)
        );
        builder.header(AUTHORIZATION_HEADER, formatAuthorizationForTarget(request.uri(), value));
        return builder.build();
    }

    private static String formatAuthorizationForTarget(URI uri, String token) {
        if (isLoopbackHost(uri)) {
            return toMockAuthorizationValue(token);
        }

        String trimmed = token == null ? "" : token.trim();
        String mockPrefix = MOCK_AUTHORIZATION_SCHEME + " ";
        if (trimmed.regionMatches(true, 0, mockPrefix, 0, mockPrefix.length())) {
            return trimmed.substring(mockPrefix.length()).trim();
        }
        return trimmed;
    }

    private static String toMockAuthorizationValue(String token) {
        String trimmed = token == null ? "" : token.trim();
        String mockPrefix = MOCK_AUTHORIZATION_SCHEME + " ";
        if (trimmed.regionMatches(true, 0, mockPrefix, 0, mockPrefix.length())) {
            return trimmed;
        }
        return mockPrefix + trimmed;
    }

    private static HttpRequest withTraceIdHeader(HttpRequest request) {
        if (request.headers().firstValue(X_TRACE_ID_HEADER).isPresent()) {
            return request;
        }

        HttpRequest.Builder builder = Helpers.copy(
            request,
            (name, ignoredValue) -> !X_TRACE_ID_HEADER.equalsIgnoreCase(name)
        );
        builder.header(X_TRACE_ID_HEADER, UUID.randomUUID().toString());
        return builder.build();
    }

    private static Optional<String> ensureValidAccessToken(Hook.BeforeRequestContext context) {
        AccessTokenState state = ACCESS_TOKEN.get();
        if (isTokenUsable(state)) {
            return Optional.of(state.token);
        }

        synchronized (REFRESH_LOCK) {
            state = ACCESS_TOKEN.get();
            if (isTokenUsable(state)) {
                return Optional.of(state.token);
            }

            Optional<String> refreshed = refreshAccessToken(context);
            if (refreshed.isPresent()) {
                cacheToken(refreshed.get());
            }

            AccessTokenState latest = ACCESS_TOKEN.get();
            if (isTokenUsable(latest)) {
                return Optional.of(latest.token);
            }
            return Optional.empty();
        }
    }

    private static Optional<String> refreshAccessToken(Hook.BeforeRequestContext context) {
        Optional<String> programUid = getSecurityValue(context.securitySource(), "programUid");
        Optional<String> hmacKey = getSecurityValue(context.securitySource(), "hmacKey");
        if (programUid.isEmpty() || hmacKey.isEmpty()) {
            return Optional.empty();
        }

        String refreshToken = generateRefreshToken(programUid.get(), hmacKey.get());
        HttpRequest authRequest = HttpRequest.newBuilder(buildAuthUri(context.baseUrl()))
            .POST(HttpRequest.BodyPublishers.noBody())
            .header("Accept", "application/json")
            .header("user-agent", SDKConfiguration.USER_AGENT)
            .header(AUTHORIZATION_HEADER, formatAuthorizationForTarget(buildAuthUri(context.baseUrl()), refreshToken))
            .header(X_TRACE_ID_HEADER, UUID.randomUUID().toString())
            .build();

        URI authUri = authRequest.uri();
        if (isLoopbackHost(authUri)) {
            authRequest = Helpers.copy(authRequest)
                .header("x-speakeasy-test-name", AUTH_OPERATION_ID)
                .header("x-speakeasy-test-instance-id", UUID.randomUUID().toString())
                .build();
        }

        try {
            HttpResponse<java.io.InputStream> response = context.sdkConfiguration().client().send(authRequest);
            if (response.statusCode() != 201) {
                return Optional.empty();
            }
            byte[] bodyBytes = response.body().readAllBytes();
            return extractToken(bodyBytes);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return Optional.empty();
        }
    }

    private static URI buildAuthUri(String baseUrl) {
        String normalized = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        return URI.create(normalized + "/auth");
    }

    private static boolean isLoopbackHost(URI uri) {
        String host = uri.getHost();
        if (host == null) {
            return false;
        }
        return "localhost".equalsIgnoreCase(host) || "127.0.0.1".equals(host) || "::1".equals(host);
    }

    private static Optional<String> extractToken(byte[] bodyBytes) {
        try {
            JsonNode node = MAPPER.readTree(bodyBytes);
            JsonNode token = node.get("token");
            if (token == null) {
                return Optional.empty();
            }
            String value = token.asText(null);
            if (value == null || value.isBlank()) {
                return Optional.empty();
            }
            return Optional.of(value);
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    private static void cacheToken(String token) {
        long expiresAt = parseTokenExpiry(token).orElseGet(() -> Instant.now().getEpochSecond() + TOKEN_TTL_SECONDS);
        ACCESS_TOKEN.set(new AccessTokenState(token, expiresAt));
    }

    private static Optional<Long> parseTokenExpiry(String token) {
        String[] parts = token.split("\\.");
        if (parts.length < 2) {
            return Optional.empty();
        }
        try {
            byte[] payloadBytes = Base64.getUrlDecoder().decode(parts[1]);
            JsonNode payload = MAPPER.readTree(payloadBytes);
            JsonNode exp = payload.get("exp");
            if (exp != null && exp.canConvertToLong()) {
                return Optional.of(exp.asLong());
            }
            JsonNode iat = payload.get("iat");
            if (iat != null && iat.canConvertToLong()) {
                return Optional.of(iat.asLong() + TOKEN_TTL_SECONDS);
            }
            return Optional.empty();
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    private static boolean isTokenUsable(AccessTokenState state) {
        if (state == null || state.token == null || state.token.isBlank()) {
            return false;
        }
        return Instant.now().getEpochSecond() < (state.expiresAtEpochSeconds - TOKEN_REFRESH_SKEW_SECONDS);
    }

    private static Optional<String> getSecurityValue(Optional<SecuritySource> securitySource, String name) {
        if (securitySource.isEmpty()) {
            return Optional.empty();
        }

        Object security = securitySource.get().getSecurity();
        if (security == null) {
            return Optional.empty();
        }

        return com.newline53.sdk.utils.Security.findStringValueWhereMetadataNameIs(security, name)
            .filter(value -> !value.isBlank());
    }

    private static String escapeJson(String value) {
        String escaped = value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\b", "\\b")
            .replace("\f", "\\f")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
        return escaped;
    }

    private static String base64UrlEncode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static byte[] hmacSha512(String key, String input) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(keySpec);
            return mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to compute HMAC SHA-512 signature", e);
        }
    }

    private static final class AccessTokenState {
        private final String token;
        private final long expiresAtEpochSeconds;

        private AccessTokenState(String token, long expiresAtEpochSeconds) {
            this.token = token;
            this.expiresAtEpochSeconds = expiresAtEpochSeconds;
        }
    }
}