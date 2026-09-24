package com.newline53.sdk.hooks;

//
// This file is written once by speakeasy code generation and
// thereafter will not be overwritten by speakeasy updates. As a
// consequence any customization of this class will be preserved.
//

public final class SDKHooks {

    private SDKHooks() {
        // prevent instantiation
    }

    public static void initialize(com.newline53.sdk.utils.Hooks hooks) {
        registerSyncHooks(hooks, new AuthHook());
    }

    public static void initialize(com.newline53.sdk.utils.AsyncHooks asyncHooks) {
        registerAsyncHooks(asyncHooks, new AuthHook());
    }

    private static void registerSyncHooks(com.newline53.sdk.utils.Hooks hooks, AuthHook authHook) {
        // register synchronous hooks here
        hooks.registerBeforeRequest(authHook.beforeRequest());
        hooks.registerAfterSuccess(authHook.afterSuccess());
        // hooks.registerAfterError(...);

        // for more information see
        // https://www.speakeasy.com/docs/additional-features/sdk-hooks
    }

    private static void registerAsyncHooks(com.newline53.sdk.utils.AsyncHooks asyncHooks, AuthHook authHook) {
        // register async hooks here
        asyncHooks.registerBeforeRequest(com.newline53.sdk.utils.HookAdapters.toAsync(authHook.beforeRequest()));
        asyncHooks.registerAfterSuccess(com.newline53.sdk.utils.HookAdapters.toAsync(authHook.afterSuccess()));
        // asyncHooks.registerAfterError(...);
        
        // NOTE: If you have existing synchronous hooks, you can adapt them using HookAdapters:
        // asyncHooks.registerAfterError(org.openapis.openapi.utils.HookAdapters.adapt(mySyncHook));
        
        // PERFORMANCE TIP: For better performance, implement async hooks directly using
        // non-blocking I/O (NIO) APIs instead of adapting synchronous hooks, as adapters
        // offload execution to the ForkJoinPool which can introduce overhead.

        // for more information see
        // https://www.speakeasy.com/docs/additional-features/sdk-hooks
    }

}
