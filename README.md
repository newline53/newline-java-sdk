# Newline Java SDK

Developer-friendly and type-safe Java SDK built to leverage the Newline Platform APIs.

<!-- No Summary [summary] -->

<!-- Start Table of Contents [toc] -->
## Table of Contents
<!-- $toc-max-depth=2 -->
* [Newline Java SDK](#newline-java-sdk)
  * [SDK Installation](#sdk-installation)
  * [SDK Example Usage](#sdk-example-usage)
  * [Asynchronous Support](#asynchronous-support)
  * [Authentication](#authentication)
  * [Available Resources and Operations](#available-resources-and-operations)
  * [Error Handling](#error-handling)
  * [Server Selection](#server-selection)
  * [Custom HTTP Client](#custom-http-client)
  * [Debugging](#debugging)
  * [Jackson Configuration](#jackson-configuration)
* [Development](#development)
  * [Maturity](#maturity)
  * [Contributions](#contributions)
  * [License](#license)
  * [See Also](#see-also)

<!-- End Table of Contents [toc] -->

## SDK Installation

### Getting started

JDK 11 or later is required.

The samples below show how a published SDK artifact is used:

Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    implementation 'com.github.newline53:newline-java-sdk:main-SNAPSHOT'
}
```

Maven:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependency>
    <groupId>com.github.newline53</groupId>
    <artifactId>newline-java-sdk</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```

### How to build

After cloning the git repository to your file system you can build the SDK artifact from source to the `build` directory by running `./gradlew build` on \*nix systems or `gradlew.bat` on Windows systems.

If you wish to build from source and publish the SDK artifact to your local Maven repository (on your filesystem) then use the following command (after cloning the git repo locally):

On \*nix:

```bash
./gradlew publishToMavenLocal -Pskip.signing
```

On Windows:

```bash
gradlew.bat publishToMavenLocal -Pskip.signing
```

<!-- No SDK Installation [installation] -->

<!-- Start SDK Example Usage [usage] -->
## SDK Example Usage

### Example

```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GenerateAuthTokenResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GenerateAuthTokenResponse res = sdk.auth().generateToken()
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
#### Asynchronous Call
An asynchronous SDK client is also available that returns a [`CompletableFuture<T>`][comp-fut]. See [Asynchronous Support](#asynchronous-support) for more details on async benefits and reactive library integration.
```java
package hello.world;

import com.newline53.sdk.AsyncNewlineSDK;
import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.async.GenerateAuthTokenResponse;
import java.util.concurrent.CompletableFuture;

public class Application {

    public static void main(String[] args) {

        AsyncNewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build()
            .toAsync();

        CompletableFuture<GenerateAuthTokenResponse> resFut = sdk.auth().generateToken()
                .call();

        resFut.thenAccept(res -> {
            if (res.object().isPresent()) {
                System.out.println(res.object().get());
            }
        });
    }
}
```

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
<!-- End SDK Example Usage [usage] -->

<!-- Start Asynchronous Support [async-support] -->
## Asynchronous Support

The SDK provides comprehensive asynchronous support using Java's [`CompletableFuture<T>`][comp-fut] and [Reactive Streams `Publisher<T>`][reactive-streams] APIs. This design makes no assumptions about your choice of reactive toolkit, allowing seamless integration with any reactive library.

<details>
<summary>Why Use Async?</summary>

Asynchronous operations provide several key benefits:

- **Non-blocking I/O**: Your threads stay free for other work while operations are in flight
- **Better resource utilization**: Handle more concurrent operations with fewer threads
- **Improved scalability**: Build highly responsive applications that can handle thousands of concurrent requests
- **Reactive integration**: Works seamlessly with reactive streams and backpressure handling

</details>

<details>
<summary>Reactive Library Integration</summary>

The SDK returns [Reactive Streams `Publisher<T>`][reactive-streams] instances for operations dealing with streams involving multiple I/O interactions. We use Reactive Streams instead of JDK Flow API to provide broader compatibility with the reactive ecosystem, as most reactive libraries natively support Reactive Streams.

**Why Reactive Streams over JDK Flow?**
- **Broader ecosystem compatibility**: Most reactive libraries (Project Reactor, RxJava, Akka Streams, etc.) natively support Reactive Streams
- **Industry standard**: Reactive Streams is the de facto standard for reactive programming in Java
- **Better interoperability**: Seamless integration without additional adapters for most use cases

**Integration with Popular Libraries:**
- **Project Reactor**: Use `Flux.from(publisher)` to convert to Reactor types
- **RxJava**: Use `Flowable.fromPublisher(publisher)` for RxJava integration
- **Akka Streams**: Use `Source.fromPublisher(publisher)` for Akka Streams integration
- **Vert.x**: Use `ReadStream.fromPublisher(vertx, publisher)` for Vert.x reactive streams
- **Mutiny**: Use `Multi.createFrom().publisher(publisher)` for Quarkus Mutiny integration

**For JDK Flow API Integration:**
If you need JDK Flow API compatibility (e.g., for Quarkus/Mutiny 2), you can use adapters:
```java
// Convert Reactive Streams Publisher to Flow Publisher
Flow.Publisher<T> flowPublisher = FlowAdapters.toFlowPublisher(reactiveStreamsPublisher);

// Convert Flow Publisher to Reactive Streams Publisher
Publisher<T> reactiveStreamsPublisher = FlowAdapters.toPublisher(flowPublisher);
```

For standard single-response operations, the SDK returns `CompletableFuture<T>` for straightforward async execution.

</details>

<details>
<summary>Supported Operations</summary>

Async support is available for:

- **[Server-sent Events](#server-sent-event-streaming)**: Stream real-time events with Reactive Streams `Publisher<T>`
- **[JSONL Streaming](#jsonl-streaming)**: Process streaming JSON lines asynchronously
- **[Pagination](#pagination)**: Iterate through paginated results using `callAsPublisher()` and `callAsPublisherUnwrapped()`
- **[File Uploads](#file-uploads)**: Upload files asynchronously with progress tracking
- **[File Downloads](#file-downloads)**: Download files asynchronously with streaming support
- **[Standard Operations](#example)**: All regular API calls return `CompletableFuture<T>` for async execution

</details>

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
[reactive-streams]: https://www.reactive-streams.org/
<!-- End Asynchronous Support [async-support] -->

<!-- Start Authentication [security] -->
## Authentication

### Per-Client Security Schemes

This SDK supports the following security scheme globally:

| Name                       | Type | Scheme      |
| -------------------------- | ---- | ----------- |
| `programUid`<br/>`hmacKey` | http | Custom HTTP |

You can set the security parameters through the `security` builder method when initializing the SDK client instance. For example:
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GenerateAuthTokenResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GenerateAuthTokenResponse res = sdk.auth().generateToken()
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
<!-- End Authentication [security] -->

<!-- Start Available Resources and Operations [operations] -->
## Available Resources and Operations

<details open>
<summary>Available methods</summary>

### [Auth](docs/sdks/auth/README.md)

* [generateToken](docs/sdks/auth/README.md#generatetoken) - Generate an authentication token

### [CombinedTransfers](docs/sdks/combinedtransfers/README.md)

* [list](docs/sdks/combinedtransfers/README.md#list) - List Combined Transfers
* [create](docs/sdks/combinedtransfers/README.md#create) - Create a new Combined Transfer
* [get](docs/sdks/combinedtransfers/README.md#get) - Get a single Combined Transfer

### [CustodialAccounts](docs/sdks/custodialaccounts/README.md)

* [list](docs/sdks/custodialaccounts/README.md#list) - List Custodial Accounts
* [get](docs/sdks/custodialaccounts/README.md#get) - Get a single Custodial Account
* [listClosingBalances](docs/sdks/custodialaccounts/README.md#listclosingbalances) - List Custodial Account Closing Balances
* [getClosingBalance](docs/sdks/custodialaccounts/README.md#getclosingbalance) - Get a single Custodial Account Closing Balance

### [CustomerProducts](docs/sdks/customerproducts/README.md)

* [list](docs/sdks/customerproducts/README.md#list) - List Customer Products
* [onboard](docs/sdks/customerproducts/README.md#onboard) - Onboard Customer onto a Product
* [get](docs/sdks/customerproducts/README.md#get) - Get a single Customer Product

### [Customers](docs/sdks/customers/README.md)

* [list](docs/sdks/customers/README.md#list) - Get a list of Customers
* [create](docs/sdks/customers/README.md#create) - Create a new Customer
* [get](docs/sdks/customers/README.md#get) - Get a single Customer
* [update](docs/sdks/customers/README.md#update) - Adjust Customer Data
* [delete](docs/sdks/customers/README.md#delete) - Archive a Customer

### [Pools](docs/sdks/pools/README.md)

* [list](docs/sdks/pools/README.md#list) - List Pools
* [get](docs/sdks/pools/README.md#get) - Get a single Pool

### [Products](docs/sdks/products/README.md)

* [list](docs/sdks/products/README.md#list) - List Products
* [get](docs/sdks/products/README.md#get) - Get a single Product

### [Returns](docs/sdks/returns/README.md)

* [list](docs/sdks/returns/README.md#list) - List Returns
* [create](docs/sdks/returns/README.md#create) - Create a new Return
* [get](docs/sdks/returns/README.md#get) - Get a single Return

### [SyntheticAccountClosingBalances](docs/sdks/syntheticaccountclosingbalances/README.md)

* [get](docs/sdks/syntheticaccountclosingbalances/README.md#get) - Get a single Synthetic Account Closing Balance

### [SyntheticAccounts](docs/sdks/syntheticaccounts/README.md)

* [listTypes](docs/sdks/syntheticaccounts/README.md#listtypes) - List Synthetic Account Types
* [getType](docs/sdks/syntheticaccounts/README.md#gettype) - Get a Single Synthetic Account Type
* [list](docs/sdks/syntheticaccounts/README.md#list) - List Synthetic Accounts
* [create](docs/sdks/syntheticaccounts/README.md#create) - Create a New Synthetic Account
* [retrieve](docs/sdks/syntheticaccounts/README.md#retrieve) - Get a single Synthetic Account
* [update](docs/sdks/syntheticaccounts/README.md#update) - Update the Synthetic Account metadata
* [delete](docs/sdks/syntheticaccounts/README.md#delete) - Archive a Synthetic Account
* [getClosingBalances](docs/sdks/syntheticaccounts/README.md#getclosingbalances) - List Synthetic Account Closing Balances

### [Transactions](docs/sdks/transactions/README.md)

* [list](docs/sdks/transactions/README.md#list) - List Transactions
* [get](docs/sdks/transactions/README.md#get) - Get a single Transaction
* [authorize](docs/sdks/transactions/README.md#authorize) - Approve or deny a transaction
* [listEvents](docs/sdks/transactions/README.md#listevents) - List Transaction Events
* [getTransactionEvent](docs/sdks/transactions/README.md#gettransactionevent) - Get a single Transaction Event
* [listSyntheticLineItems](docs/sdks/transactions/README.md#listsyntheticlineitems) - List Synthetic Line Items
* [getSyntheticLineItem](docs/sdks/transactions/README.md#getsyntheticlineitem) - Get a single Synthetic Line Item
* [listCustodialLineItems](docs/sdks/transactions/README.md#listcustodiallineitems) - List Custodial Line Items
* [getCustodialLineItem](docs/sdks/transactions/README.md#getcustodiallineitem) - Get a single Custodial Line Item

### [Transfers](docs/sdks/transfers/README.md)

* [list](docs/sdks/transfers/README.md#list) - List Transfers
* [create](docs/sdks/transfers/README.md#create) - Initiate a Transfer
* [get](docs/sdks/transfers/README.md#get) - Get a single Transfer
* [cancel](docs/sdks/transfers/README.md#cancel) - Cancel a Transfer

### [VirtualReferenceNumbers](docs/sdks/virtualreferencenumbers/README.md)

* [list](docs/sdks/virtualreferencenumbers/README.md#list) - List Virtual Reference Numbers
* [create](docs/sdks/virtualreferencenumbers/README.md#create) - Create a new Virtual Reference Number
* [get](docs/sdks/virtualreferencenumbers/README.md#get) - Get a single Virtual Reference Number
* [update](docs/sdks/virtualreferencenumbers/README.md#update) - Edit a Virtual Reference Number
* [archive](docs/sdks/virtualreferencenumbers/README.md#archive) - Archive a single Virtual Reference Number
* [lock](docs/sdks/virtualreferencenumbers/README.md#lock) - Lock a single Virtual Reference Number
* [unlock](docs/sdks/virtualreferencenumbers/README.md#unlock) - Unlock a single Virtual Reference Number

</details>
<!-- End Available Resources and Operations [operations] -->

<!-- Start Error Handling [errors] -->
## Error Handling

Handling errors in this SDK should largely match your expectations. All operations return a response object or raise an exception.


[`NewlineException`](./src/main/java/models/errors/NewlineException.java) is the base class for all HTTP error responses. It has the following properties:

| Method           | Type                        | Description                                                              |
| ---------------- | --------------------------- | ------------------------------------------------------------------------ |
| `message()`      | `String`                    | Error message                                                            |
| `code()`         | `int`                       | HTTP response status code eg `404`                                       |
| `headers`        | `Map<String, List<String>>` | HTTP response headers                                                    |
| `body()`         | `byte[]`                    | HTTP body as a byte array. Can be empty array if no body is returned.    |
| `bodyAsString()` | `String`                    | HTTP body as a UTF-8 string. Can be empty string if no body is returned. |
| `rawResponse()`  | `HttpResponse<?>`           | Raw HTTP response (body already read and not available for re-read)      |

### Example
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.NewlineException;
import com.newline53.sdk.models.errors.OnboardCustomerProductUnprocessableEntityException;
import com.newline53.sdk.models.operations.OnboardCustomerProductError;
import com.newline53.sdk.models.operations.OnboardCustomerProductRequest;
import com.newline53.sdk.models.operations.OnboardCustomerProductResponse;
import java.io.UncheckedIOException;
import java.lang.Exception;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws OnboardCustomerProductUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();
        try {

            OnboardCustomerProductRequest req = OnboardCustomerProductRequest.builder()
                    .customerUid("S62MaHx6WwsqG9vQ")
                    .productUid("pQtTCSXz57fuefzp")
                    .build();

            OnboardCustomerProductResponse res = sdk.customerProducts().onboard()
                    .request(req)
                    .call();

            if (res.object().isPresent()) {
                System.out.println(res.object().get());
            }
        } catch (NewlineException ex) { // all SDK exceptions inherit from NewlineException

            // ex.ToString() provides a detailed error message including
            // HTTP status code, headers, and error payload (if any)
            System.out.println(ex);

            // Base exception fields
            var rawResponse = ex.rawResponse();
            var headers = ex.headers();
            var contentType = headers.first("Content-Type");
            int statusCode = ex.code();
            Optional<byte[]> responseBody = ex.body();

            // different error subclasses may be thrown 
            // depending on the service call
            if (ex instanceof OnboardCustomerProductUnprocessableEntityException) {
                var e = (OnboardCustomerProductUnprocessableEntityException) ex;
                // Check error data fields
                e.data().ifPresent(payload -> {
                      Optional<List<OnboardCustomerProductError>> errors = payload.errors();
                      Optional<Long> status = payload.status();
                });
            }

            // An underlying cause may be provided. If the error payload 
            // cannot be deserialized then the deserialization exception 
            // will be set as the cause.
            if (ex.getCause() != null) {
                var cause = ex.getCause();
            }
        } catch (UncheckedIOException ex) {
            // handle IO error (connection, timeout, etc)
        }    }
}
```

### Error Classes
**Primary error:**
* [`NewlineException`](./src/main/java/models/errors/NewlineException.java): The base class for HTTP error responses.

<details><summary>Less common errors (38)</summary>

<br />

**Network errors:**
* `java.io.IOException` (always wrapped by `java.io.UncheckedIOException`). Commonly encountered subclasses of
`IOException` include `java.net.ConnectException`, `java.net.SocketTimeoutException`, `EOFException` (there are
many more subclasses in the JDK platform).

**Inherit from [`NewlineException`](./src/main/java/models/errors/NewlineException.java)**:
* [`com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException.java): A Synthetic Account is not updated if a required parameter is missing. Status code `400`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutTransfersUidCancelBadRequestException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutTransfersUidCancelBadRequestException.java): The transfer is not eligible for cancellation. Status code `400`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException.java): Bad authorization request. Status code `400`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostReturnsBadRequestException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostReturnsBadRequestException.java): Creation Error. Status code `400`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException.java): Client authorization disabled. (The Program is not configured for Client Authorization). Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetReturnsForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetReturnsForbiddenException.java): Denied access to Returns. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostReturnsForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostReturnsForbiddenException.java): Denied access to Returns. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetReturnsUidForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetReturnsUidForbiddenException.java): Denied access to Returns. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetCombinedTransfersForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetCombinedTransfersForbiddenException.java): Denied access to Combined Transfers. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException.java): Denied access to Combined Transfers. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException.java): Denied access to Combined Transfers. Status code `403`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUidNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUidNotFoundException.java): The Virtual Reference Number is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidNotFoundException.java): The Virtual Reference Number is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.DeleteVirtualReferenceNumbersUidNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.DeleteVirtualReferenceNumbersUidNotFoundException.java): The Virtual Reference Number is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException.java): The Virtual Reference Number is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockNotFoundException.java): The Virtual Reference Number is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetReturnsUidNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetReturnsUidNotFoundException.java): Unknown Return. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException.java): The Combined Transfer is not found. Status code `404`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.ConflictException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.ConflictException.java): A new Synthetic Account is NOT created if the external_uid given is present but not unique. Status code `409`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.OnboardCustomerProductUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.OnboardCustomerProductUnprocessableEntityException.java): There was a problem with the request body. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.DeleteSyntheticAccountUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.DeleteSyntheticAccountUnprocessableEntityException.java): A Synthetic Account is not archived. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutTransfersUidCancelUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutTransfersUidCancelUnprocessableEntityException.java): The transfer could not be canceled. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException.java): An exception occurred while authorizing transaction. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUnprocessableEntityException.java): Failed to retrieve Virtual Reference Numbers. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException.java): Creation Error. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnprocessableEntityException.java): Failed to update Virtual Reference Number. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException.java): The Virtual Reference Number could not be locked. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockUnprocessableEntityException.java): The Virtual Reference Number could not be unlocked. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetReturnsUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetReturnsUnprocessableEntityException.java): Failed to retrieve Returns. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException.java): Creation Error. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.GetCombinedTransfersUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.GetCombinedTransfersUnprocessableEntityException.java): Failed to retrieve Combined Transfers. Status code `422`. Applicable to 1 of 52 methods.*
* [`com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException`](./src/main/java/models/errors/com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException.java): Creation Error. Status code `422`. Applicable to 1 of 52 methods.*


</details>

\* Check [the method documentation](#available-resources-and-operations) to see if the error is applicable.
<!-- End Error Handling [errors] -->

<!-- Start Server Selection [server] -->
## Server Selection

### Select Server by Name

You can override the default server globally using the `.server(AvailableServers server)` builder method when initializing the SDK client instance. The selected server will then be used as the default on the operations that use it. This table lists the names associated with the available servers:

| Name      | Server                                 | Description |
| --------- | -------------------------------------- | ----------- |
| `sandbox` | `https://sandbox.newline53.com/api/v1` | Sandbox     |
| `prod`    | `https://api.newline53.com/api/v1`     | Production  |

#### Example

```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GenerateAuthTokenResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .server(NewlineSDK.AvailableServers.SANDBOX)
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GenerateAuthTokenResponse res = sdk.auth().generateToken()
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Override Server URL Per-Client

The default server can also be overridden globally using the `.serverURL(String serverUrl)` builder method when initializing the SDK client instance. For example:
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GenerateAuthTokenResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .serverURL("https://sandbox.newline53.com/api/v1")
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GenerateAuthTokenResponse res = sdk.auth().generateToken()
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
<!-- End Server Selection [server] -->

<!-- Start Custom HTTP Client [http-client] -->
## Custom HTTP Client

The Java SDK makes API calls using an `HTTPClient` that wraps the native
[HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html). This
client provides the ability to attach hooks around the request lifecycle that can be used to modify the request or handle
errors and response.

The `HTTPClient` interface allows you to either use the default `SpeakeasyHTTPClient` that comes with the SDK,
or provide your own custom implementation with customized configuration such as custom executors, SSL context,
connection pools, and other HTTP client settings.

The interface provides synchronous (`send`) methods and asynchronous (`sendAsync`) methods. The `sendAsync` method
is used to power the async SDK methods and returns a `CompletableFuture<HttpResponse<Blob>>` for non-blocking operations.

The following example shows how to add a custom header and handle errors:

```java
import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.utils.HTTPClient;
import com.newline53.sdk.utils.SpeakeasyHTTPClient;
import com.newline53.sdk.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        // Create a custom HTTP client with hooks
        HTTPClient httpClient = new HTTPClient() {
            private final HTTPClient defaultClient = new SpeakeasyHTTPClient();
            
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                // Add custom header and timeout using Utils.copy()
                HttpRequest modifiedRequest = Utils.copy(request)
                    .header("x-custom-header", "custom value")
                    .timeout(Duration.ofSeconds(30))
                    .build();
                    
                try {
                    HttpResponse<InputStream> response = defaultClient.send(modifiedRequest);
                    // Log successful response
                    System.out.println("Request successful: " + response.statusCode());
                    return response;
                } catch (Exception error) {
                    // Log error
                    System.err.println("Request failed: " + error.getMessage());
                    throw error;
                }
            }
        };

        NewlineSDK sdk = NewlineSDK.builder()
            .client(httpClient)
            .build();
    }
}
```

<details>
<summary>Custom HTTP Client Configuration</summary>

You can also provide a completely custom HTTP client with your own configuration:

```java
import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.utils.HTTPClient;
import com.newline53.sdk.utils.Blob;
import com.newline53.sdk.utils.ResponseWithBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args) {
        // Custom HTTP client with custom configuration
        HTTPClient customHttpClient = new HTTPClient() {
            private final HttpClient client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(10))
                .connectTimeout(Duration.ofSeconds(30))
                // .sslContext(customSslContext) // Add custom SSL context if needed
                .build();

            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            }

            @Override
            public CompletableFuture<HttpResponse<Blob>> sendAsync(HttpRequest request) {
                // Convert response to HttpResponse<Blob> for async operations
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                    .thenApply(resp -> new ResponseWithBody<>(resp, Blob::from));
            }
        };

        NewlineSDK sdk = NewlineSDK.builder()
            .client(customHttpClient)
            .build();
    }
}
```

</details>

You can also enable debug logging on the default `SpeakeasyHTTPClient`:

```java
import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.utils.SpeakeasyHTTPClient;

public class Application {
    public static void main(String[] args) {
        SpeakeasyHTTPClient httpClient = new SpeakeasyHTTPClient();
        httpClient.enableDebugLogging(true);

        NewlineSDK sdk = NewlineSDK.builder()
            .client(httpClient)
            .build();
    }
}
```
<!-- End Custom HTTP Client [http-client] -->

<!-- Start Debugging [debug] -->
## Debugging

### Debug & Logging

#### SLF4j Logging
This SDK uses [SLF4j](https://www.slf4j.org/) for structured logging across HTTP requests, retries, pagination, streaming, and hooks. SLF4j provides comprehensive visibility into SDK operations.

**Log Levels:**
- **DEBUG**: High-level operations (HTTP requests/responses, retry attempts, page fetches, hook execution, stream lifecycle)
- **TRACE**: Detailed information (request/response bodies, backoff calculations, individual items processed)

**Configuration:**

Add your preferred SLF4j implementation to your project. For example, using Logback:

```gradle
dependencies {
    implementation 'ch.qos.logback:logback-classic:1.4.14'
}
```

Configure logging levels in your `logback.xml`:

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- SDK-wide logging -->
    <logger name="com.newline53.sdk" level="DEBUG"/>
    
    <!-- Component-specific logging -->
    <logger name="com.newline53.sdk.utils.SpeakeasyHTTPClient" level="DEBUG"/>
    <logger name="com.newline53.sdk.utils.Retries" level="DEBUG"/>
    <logger name="com.newline53.sdk.utils.pagination" level="DEBUG"/>
    <logger name="com.newline53.sdk.utils.Hooks" level="TRACE"/>
    
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

**What Gets Logged:**
- **HTTP Client**: Request/response details, headers (with sensitive headers redacted), bodies (at TRACE level)
- **Retries**: Retry attempts, backoff delays, exhaustion, non-retryable exceptions
- **Pagination**: Page fetches, pagination state, errors
- **Streaming**: Stream initialization, item processing, closure
- **Hooks**: Hook execution counts, operation IDs, exceptions

#### Legacy Debug Logging
For backward compatibility, you can still use the legacy debug logging method:

```java
SDK.builder()
    .enableHTTPDebugLogging(true)
    .build();
```
Example output:
```
Sending request: http://localhost:35123/bearer#global GET
Request headers: {Accept=[application/json], Authorization=[******], Client-Level-Header=[added by client], Idempotency-Key=[some-key], x-speakeasy-user-agent=[speakeasy-sdk/java 0.0.1 internal 0.1.0 org.openapis.openapi]}
Received response: (GET http://localhost:35123/bearer#global) 200
Response headers: {access-control-allow-credentials=[true], access-control-allow-origin=[*], connection=[keep-alive], content-length=[50], content-type=[application/json], date=[Wed, 09 Apr 2025 01:43:29 GMT], server=[gunicorn/19.9.0]}
Response body:
{
  "authenticated": true, 
  "token": "global"
}
```
__WARNING__: Debug logging should only be used for temporary debugging purposes. Leaving this option on in a production system could expose credentials/secrets in logs. <i>Authorization</i> headers are redacted by default. You can specify additional redacted header names via `SpeakeasyHTTPClient.setRedactedHeaders`.

__NOTE__: This is a convenience method that calls `HTTPClient.enableDebugLogging()`. The `SpeakeasyHTTPClient` honors this setting. If you are using a custom HTTP client, it is up to the custom client to honor this setting.


#### JDK HTTP Client Logging
Another option is to set the System property `-Djdk.httpclient.HttpClient.log=all`. However, this option does not log request/response bodies.
<!-- End Debugging [debug] -->

<!-- Start Jackson Configuration [jackson] -->
## Jackson Configuration

The SDK ships with a pre-configured Jackson [`ObjectMapper`][jackson-databind] accessible via
`JSON.getMapper()`. It is set up with type modules, strict deserializers, and the feature flags
needed for full SDK compatibility (including ISO-8601 `OffsetDateTime` serialization):

```java
import com.newline53.sdk.utils.JSON;

String json = JSON.getMapper().writeValueAsString(response);
```

To compose with your own `ObjectMapper`, register the provided `NewlineJavaJacksonModule`, which
bundles all the same modules and feature flags as a single plug-and-play module:

```java
import com.newline53.sdk.utils.NewlineJavaJacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper myMapper = new ObjectMapper()
    .registerModule(new NewlineJavaJacksonModule());

String json = myMapper.writeValueAsString(response);
```

[jackson-databind]: https://github.com/FasterXML/jackson-databind
[jackson-jsr310]: https://github.com/FasterXML/jackson-modules-java8/tree/master/datetime
<!-- End Jackson Configuration [jackson] -->

<!-- Placeholder for Future Speakeasy SDK Sections -->

# Development

## Maturity

This SDK is in beta, and there may be breaking changes between versions without a major version update. Therefore, we recommend pinning usage
to a specific package version. This way, you can install the same version each time without breaking changes unless you are intentionally
looking for the latest version.

## Contributions

While we value open-source contributions to this SDK, this library is generated programmatically. Any manual changes added to internal files will be overwritten on the next generation.
We look forward to hearing your feedback. Feel free to open a PR or an issue with a proof of concept and we'll do our best to include it in a future release.

## License

[Apache 2.0](LICENSE)

## See Also

- [Newline API Documentation](https://developers.newline53.com)
- [Newline MCP Server](https://github.com/newline53/newline-mcp-server)
- [Newline Postman Collections](https://www.postman.com/newline53)
