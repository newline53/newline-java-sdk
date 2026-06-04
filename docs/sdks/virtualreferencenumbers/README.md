# VirtualReferenceNumbers

## Overview

### Available Operations

* [list](#list) - List Virtual Reference Numbers
* [create](#create) - Create a new Virtual Reference Number
* [get](#get) - Get a single Virtual Reference Number
* [update](#update) - Edit a Virtual Reference Number
* [archive](#archive) - Archive a single Virtual Reference Number
* [lock](#lock) - Lock a single Virtual Reference Number
* [unlock](#unlock) - Unlock a single Virtual Reference Number

## list

Retrieves a list of Virtual Reference Numbers (VRNs) associated with the specified Synthetic Account. Supports filtering by status and other attributes.


### Example Usage

<!-- UsageSnippet language="java" operationID="get_/virtual_reference_numbers" method="get" path="/virtual_reference_numbers" example="virtual_reference_numbers_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUnprocessableEntityException;
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersQueryParamStatus;
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersResponse;
import com.newline53.sdk.models.operations.QueryParamInstantPaymentRailRegistrationStatus;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetVirtualReferenceNumbersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetVirtualReferenceNumbersResponse res = sdk.virtualReferenceNumbers().list()
                .instantPaymentRailRegistrationStatus(QueryParamInstantPaymentRailRegistrationStatus.REGISTERED)
                .status(GetVirtualReferenceNumbersQueryParamStatus.ACTIVE)
                .syntheticAccountUid("Dg1EPao8XukUpHG8")
                .virtualReferenceNumber("1234567890123456")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                             | Type                                                                                                                                  | Required                                                                                                                              | Description                                                                                                                           | Example                                                                                                                               |
| ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `instantPaymentRailRegistrationStatus`                                                                                                | @Nullable [QueryParamInstantPaymentRailRegistrationStatus](../../models/operations/QueryParamInstantPaymentRailRegistrationStatus.md) | :heavy_minus_sign:                                                                                                                    | Registration status with Newline and Fifth Third, for RTP network acceptance.                                                         | registered                                                                                                                            |
| `status`                                                                                                                              | @Nullable [GetVirtualReferenceNumbersQueryParamStatus](../../models/operations/GetVirtualReferenceNumbersQueryParamStatus.md)         | :heavy_minus_sign:                                                                                                                    | A value indicating the overall state of this VRN.                                                                                     | active                                                                                                                                |
| `syntheticAccountUid`                                                                                                                 | @Nullable *String*                                                                                                                    | :heavy_minus_sign:                                                                                                                    | N/A                                                                                                                                   | Dg1EPao8XukUpHG8                                                                                                                      |
| `virtualReferenceNumber`                                                                                                              | @Nullable *String*                                                                                                                    | :heavy_minus_sign:                                                                                                                    | N/A                                                                                                                                   | 1234567890123456                                                                                                                      |

### Response

**[GetVirtualReferenceNumbersResponse](../../models/operations/GetVirtualReferenceNumbersResponse.md)**

### Errors

| Error Type                                                           | Status Code                                                          | Content Type                                                         |
| -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- |
| models/errors/GetVirtualReferenceNumbersUnprocessableEntityException | 422                                                                  | application/json                                                     |
| models/errors/APIException                                           | 4XX, 5XX                                                             | \*/\*                                                                |

## create

Creates a new Virtual Reference Number (VRN) for the specified Synthetic Account.

### Example Usage: create_payload

<!-- UsageSnippet language="java" operationID="post_/virtual_reference_numbers" method="post" path="/virtual_reference_numbers" example="create_payload" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersRequest;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostVirtualReferenceNumbersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostVirtualReferenceNumbersRequest req = PostVirtualReferenceNumbersRequest.builder()
                .syntheticAccountUid("Dg1EPao8XukUpHG8")
                .routingNumber("123456789")
                .externalUid("YrfDrfVRgpPgnhF5")
                .name("greenfield1")
                .build();

        PostVirtualReferenceNumbersResponse res = sdk.virtualReferenceNumbers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: pending

<!-- UsageSnippet language="java" operationID="post_/virtual_reference_numbers" method="post" path="/virtual_reference_numbers" example="pending" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersRequest;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostVirtualReferenceNumbersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostVirtualReferenceNumbersRequest req = PostVirtualReferenceNumbersRequest.builder()
                .syntheticAccountUid("Dg1EPao8XukUpHG8")
                .routingNumber("123456789")
                .externalUid("partner-generated-id")
                .name("greenfield1")
                .build();

        PostVirtualReferenceNumbersResponse res = sdk.virtualReferenceNumbers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: synthetic_account_ineligible_for_vrn

<!-- UsageSnippet language="java" operationID="post_/virtual_reference_numbers" method="post" path="/virtual_reference_numbers" example="synthetic_account_ineligible_for_vrn" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersRequest;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostVirtualReferenceNumbersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostVirtualReferenceNumbersRequest req = PostVirtualReferenceNumbersRequest.builder()
                .syntheticAccountUid("Dg1EPao8XukUpHG8")
                .routingNumber("123456789")
                .externalUid("partner-generated-id")
                .name("greenfield1")
                .build();

        PostVirtualReferenceNumbersResponse res = sdk.virtualReferenceNumbers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: vrn_creation_error_sync

<!-- UsageSnippet language="java" operationID="post_/virtual_reference_numbers" method="post" path="/virtual_reference_numbers" example="vrn_creation_error_sync" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostVirtualReferenceNumbersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersRequest;
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostVirtualReferenceNumbersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostVirtualReferenceNumbersRequest req = PostVirtualReferenceNumbersRequest.builder()
                .syntheticAccountUid("Dg1EPao8XukUpHG8")
                .routingNumber("123456789")
                .externalUid("partner-generated-id")
                .name("greenfield1")
                .build();

        PostVirtualReferenceNumbersResponse res = sdk.virtualReferenceNumbers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `request`                                                                                           | [PostVirtualReferenceNumbersRequest](../../models/operations/PostVirtualReferenceNumbersRequest.md) | :heavy_check_mark:                                                                                  | The request object to use for the request.                                                          |

### Response

**[PostVirtualReferenceNumbersResponse](../../models/operations/PostVirtualReferenceNumbersResponse.md)**

### Errors

| Error Type                                                            | Status Code                                                           | Content Type                                                          |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| models/errors/PostVirtualReferenceNumbersUnprocessableEntityException | 422                                                                   | application/json                                                      |
| models/errors/APIException                                            | 4XX, 5XX                                                              | \*/\*                                                                 |

## get

Retrieves a single Virtual Reference Number resource along with its details, including status, linked Synthetic Account, and registration metadata.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/virtual_reference_numbers/{uid}" method="get" path="/virtual_reference_numbers/{uid}" example="registered" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetVirtualReferenceNumbersUidNotFoundException;
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetVirtualReferenceNumbersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetVirtualReferenceNumbersUidResponse res = sdk.virtualReferenceNumbers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `uid`                                                                 | *String*                                                              | :heavy_check_mark:                                                    | Newline-generated unique id resource specific to the current endpoint |

### Response

**[GetVirtualReferenceNumbersUidResponse](../../models/operations/GetVirtualReferenceNumbersUidResponse.md)**

### Errors

| Error Type                                                   | Status Code                                                  | Content Type                                                 |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| models/errors/GetVirtualReferenceNumbersUidNotFoundException | 404                                                          | application/json                                             |
| models/errors/APIException                                   | 4XX, 5XX                                                     | \*/\*                                                        |

## update

Updates the metadata of an existing Virtual Reference Number. This may include changes to labels, descriptions, or Instant Payment registration settings.

### Example Usage: failed_to_update_vrn

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}" method="put" path="/virtual_reference_numbers/{uid}" example="failed_to_update_vrn" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidNotFoundException, PutVirtualReferenceNumbersUidUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidResponse res = sdk.virtualReferenceNumbers().update()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("greenfield1")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: registered

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}" method="put" path="/virtual_reference_numbers/{uid}" example="registered" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidNotFoundException, PutVirtualReferenceNumbersUidUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidResponse res = sdk.virtualReferenceNumbers().update()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("greenfield1")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: unknown_virtual_reference_number

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}" method="put" path="/virtual_reference_numbers/{uid}" example="unknown_virtual_reference_number" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidNotFoundException, PutVirtualReferenceNumbersUidUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidResponse res = sdk.virtualReferenceNumbers().update()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("greenfield1")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                       | Type                                                                                                            | Required                                                                                                        | Description                                                                                                     |
| --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `uid`                                                                                                           | *String*                                                                                                        | :heavy_check_mark:                                                                                              | Newline-generated unique id resource specific to the current endpoint                                           |
| `body`                                                                                                          | [PutVirtualReferenceNumbersUidRequestBody](../../models/operations/PutVirtualReferenceNumbersUidRequestBody.md) | :heavy_check_mark:                                                                                              | N/A                                                                                                             |

### Response

**[PutVirtualReferenceNumbersUidResponse](../../models/operations/PutVirtualReferenceNumbersUidResponse.md)**

### Errors

| Error Type                                                              | Status Code                                                             | Content Type                                                            |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| models/errors/PutVirtualReferenceNumbersUidNotFoundException            | 404                                                                     | application/json                                                        |
| models/errors/PutVirtualReferenceNumbersUidUnprocessableEntityException | 422                                                                     | application/json                                                        |
| models/errors/APIException                                              | 4XX, 5XX                                                                | \*/\*                                                                   |

## archive

Archives a Virtual Reference Number, removing it from active use. Archived VRNs cannot be used for incoming payments or reconciliation.

### Example Usage

<!-- UsageSnippet language="java" operationID="delete_/virtual_reference_numbers/{uid}" method="delete" path="/virtual_reference_numbers/{uid}" example="archived" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.DeleteVirtualReferenceNumbersUidNotFoundException;
import com.newline53.sdk.models.operations.DeleteVirtualReferenceNumbersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws DeleteVirtualReferenceNumbersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        DeleteVirtualReferenceNumbersUidResponse res = sdk.virtualReferenceNumbers().archive()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `uid`                                                                 | *String*                                                              | :heavy_check_mark:                                                    | Newline-generated unique id resource specific to the current endpoint |

### Response

**[DeleteVirtualReferenceNumbersUidResponse](../../models/operations/DeleteVirtualReferenceNumbersUidResponse.md)**

### Errors

| Error Type                                                      | Status Code                                                     | Content Type                                                    |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| models/errors/DeleteVirtualReferenceNumbersUidNotFoundException | 404                                                             | application/json                                                |
| models/errors/APIException                                      | 4XX, 5XX                                                        | \*/\*                                                           |

## lock

Locks a Virtual Reference Number to prevent new transactions or usage. This is typically used for fraud prevention or temporary deactivation.

### Example Usage: failed_to_update_vrn

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="failed_to_update_vrn" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: locked

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="locked" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: unknown_virtual_reference_number

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="unknown_virtual_reference_number" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: virtual_reference_number_archived

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="virtual_reference_number_archived" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: vrn_already_locked

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="vrn_already_locked" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: vrn_lock_cooldown_active

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/lock" method="put" path="/virtual_reference_numbers/{uid}/lock" example="vrn_lock_cooldown_active" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidLockUnprocessableEntityException;
import com.newline53.sdk.models.operations.LockReasonRequest;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockRequestBody;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidLockNotFoundException, PutVirtualReferenceNumbersUidLockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidLockResponse res = sdk.virtualReferenceNumbers().lock()
                .uid("<id>")
                .body(PutVirtualReferenceNumbersUidLockRequestBody.builder()
                    .lockReason(LockReasonRequest.ADMIN)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                               | Type                                                                                                                    | Required                                                                                                                | Description                                                                                                             |
| ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| `uid`                                                                                                                   | *String*                                                                                                                | :heavy_check_mark:                                                                                                      | Newline-generated unique id resource specific to the current endpoint                                                   |
| `body`                                                                                                                  | [PutVirtualReferenceNumbersUidLockRequestBody](../../models/operations/PutVirtualReferenceNumbersUidLockRequestBody.md) | :heavy_check_mark:                                                                                                      | N/A                                                                                                                     |

### Response

**[PutVirtualReferenceNumbersUidLockResponse](../../models/operations/PutVirtualReferenceNumbersUidLockResponse.md)**

### Errors

| Error Type                                                                  | Status Code                                                                 | Content Type                                                                |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| models/errors/PutVirtualReferenceNumbersUidLockNotFoundException            | 404                                                                         | application/json                                                            |
| models/errors/PutVirtualReferenceNumbersUidLockUnprocessableEntityException | 422                                                                         | application/json                                                            |
| models/errors/APIException                                                  | 4XX, 5XX                                                                    | \*/\*                                                                       |

## unlock

Unlocks a previously locked Virtual Reference Number, restoring its ability to receive payments and participate in reconciliation workflows.

### Example Usage

<!-- UsageSnippet language="java" operationID="put_/virtual_reference_numbers/{uid}/unlock" method="put" path="/virtual_reference_numbers/{uid}/unlock" example="registered" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockNotFoundException;
import com.newline53.sdk.models.errors.PutVirtualReferenceNumbersUidUnlockUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidUnlockResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutVirtualReferenceNumbersUidUnlockNotFoundException, PutVirtualReferenceNumbersUidUnlockUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutVirtualReferenceNumbersUidUnlockResponse res = sdk.virtualReferenceNumbers().unlock()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `uid`                                                                 | *String*                                                              | :heavy_check_mark:                                                    | Newline-generated unique id resource specific to the current endpoint |

### Response

**[PutVirtualReferenceNumbersUidUnlockResponse](../../models/operations/PutVirtualReferenceNumbersUidUnlockResponse.md)**

### Errors

| Error Type                                                                    | Status Code                                                                   | Content Type                                                                  |
| ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| models/errors/PutVirtualReferenceNumbersUidUnlockNotFoundException            | 404                                                                           | application/json                                                              |
| models/errors/PutVirtualReferenceNumbersUidUnlockUnprocessableEntityException | 422                                                                           | application/json                                                              |
| models/errors/APIException                                                    | 4XX, 5XX                                                                      | \*/\*                                                                         |