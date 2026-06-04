# Returns

## Overview

The Returns endpoints help initiate, track, and manage returns of received and originated payments. These endpoints are accessible within the Sandbox and Production environments.

**Endpoints:**

- GET [List Returns: GET /returns](https://developers.newline53.com/reference/get_returns)
- POST [Create a new Return: POST /returns](https://developers.newline53.com/reference/post_returns)
- GET [Get a single Return: GET /returns/{uid}](https://developers.newline53.com/reference/get_returns-uid)

The returns endpoint allows for the retrieval and creation of return transactions within the system.

[GET List Returns](https://newline-enterprise-group.readme.io/reference/get_returns)- Use this endpoint to retrieve a list of all return transactions. You can filter the results based on various parameters such as status or date.

[POST Create a New Return](https://newline-enterprise-group.readme.io/reference/post_returns)- This endpoint is used to Create a new return transaction. Return can only be created for eligible transactions that have already been completed.

[GET a Single Return](https://newline-enterprise-group.readme.io/reference/get_returns-uid)- Use this endpoint to retrieve details about a specific return transaction.

### Available Operations

* [list](#list) - List Returns
* [create](#create) - Create a new Return
* [get](#get) - Get a single Return

## list

Retrieves a list of return transactions. You can filter the results based on parameters such as status, date, and transaction type. This endpoint is available in Sandbox and Production environments.


### Example Usage

<!-- UsageSnippet language="java" operationID="get_/returns" method="get" path="/returns" example="returns_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetReturnsForbiddenException;
import com.newline53.sdk.models.errors.GetReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.GetReturnsResponse;
import com.newline53.sdk.models.operations.QueryParamRequestorType;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetReturnsForbiddenException, GetReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetReturnsResponse res = sdk.returns().list()
                .customerUid("Trzqy9t6j6tFGoG3")
                .requestorType(QueryParamRequestorType.CUSTOMER)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `customerUid`                                                                           | @Nullable *String*                                                                      | :heavy_minus_sign:                                                                      | N/A                                                                                     | Trzqy9t6j6tFGoG3                                                                        |
| `requestorType`                                                                         | @Nullable [QueryParamRequestorType](../../models/operations/QueryParamRequestorType.md) | :heavy_minus_sign:                                                                      | Type of the customer requesting a return.                                               | customer                                                                                |

### Response

**[GetReturnsResponse](../../models/operations/GetReturnsResponse.md)**

### Errors

| Error Type                                           | Status Code                                          | Content Type                                         |
| ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- |
| models/errors/GetReturnsForbiddenException           | 403                                                  | application/json                                     |
| models/errors/GetReturnsUnprocessableEntityException | 422                                                  | application/json                                     |
| models/errors/APIException                           | 4XX, 5XX                                             | \*/\*                                                |

## create

Initiates a Return of an ACH or wire payment.
A full ACH addenda is not available for ACH returns because an addenda record is used for the return itself; the `addenda_info` field contains the remaining available space.
For wire returns, the `wire_instruction` field is limited to 70 characters because Newline prefixes the instructions with the original wire transaction identifier (e.g., IMAD).


### Example Usage: access_to_returns

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="access_to_returns" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: ach

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="ach" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("RLYGR8CO RMA Z452208-13")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: bad_original_transaction_status

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="bad_original_transaction_status" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: cannot_return_a_return_transaction

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="cannot_return_a_return_transaction" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: cannot_return_an_ineligible_transaction

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="cannot_return_an_ineligible_transaction" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: created_return

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="created_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: expired_return_window

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="expired_return_window" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: extraneous_payment_rail_info_for_return

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="extraneous_payment_rail_info_for_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: information_unavailable_for_return

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="information_unavailable_for_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: insufficient_time_to_process_return

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="insufficient_time_to_process_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: network_code_unsupported

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="network_code_unsupported" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: return_already_exists

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="return_already_exists" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: return_creation_error

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="return_creation_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: unknown_requestor_type

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="unknown_requestor_type" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: unsupported_original_transaction_code

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="unsupported_original_transaction_code" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: unsupported_return

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="unsupported_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.AchReturnCodeRequest;
import com.newline53.sdk.models.operations.PostReturnsAchRequest;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("nwXnpBbX3A5sTki3")
                .requestingCustomerUid("Trzqy9t6j6tFGoG3")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Insufficient Funds")
                .externalUid("YrfDrfVRgpPgnhF5")
                .ach(PostReturnsAchRequest.builder()
                    .achReturnCode(AchReturnCodeRequest.R02)
                    .addendaInfo("TXN0055BADD1E cancelled")
                    .build())
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire

<!-- UsageSnippet language="java" operationID="post_/returns" method="post" path="/returns" example="wire" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostReturnsBadRequestException;
import com.newline53.sdk.models.errors.PostReturnsForbiddenException;
import com.newline53.sdk.models.errors.PostReturnsUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostReturnsRequest;
import com.newline53.sdk.models.operations.PostReturnsResponse;
import com.newline53.sdk.models.operations.PostReturnsWireRequest;
import com.newline53.sdk.models.operations.RequestorTypeRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostReturnsBadRequestException, PostReturnsForbiddenException, PostReturnsUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostReturnsRequest req = PostReturnsRequest.builder()
                .originalTransactionUid("DbxJUHVuqt3C7hGK")
                .requestingCustomerUid("EhrQZJNjCd79LLYq")
                .requestorType(RequestorTypeRequest.CUSTOMER)
                .returnReason("Refund issued by Bruno's Boxing for Order #5555555555")
                .wire(PostReturnsWireRequest.builder()
                    .wireInstructions("ORDER 5555555555")
                    .build())
                .build();

        PostReturnsResponse res = sdk.returns().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `request`                                                           | [PostReturnsRequest](../../models/operations/PostReturnsRequest.md) | :heavy_check_mark:                                                  | The request object to use for the request.                          |

### Response

**[PostReturnsResponse](../../models/operations/PostReturnsResponse.md)**

### Errors

| Error Type                                            | Status Code                                           | Content Type                                          |
| ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| models/errors/PostReturnsBadRequestException          | 400                                                   | application/json                                      |
| models/errors/PostReturnsForbiddenException           | 403                                                   | application/json                                      |
| models/errors/PostReturnsUnprocessableEntityException | 422                                                   | application/json                                      |
| models/errors/APIException                            | 4XX, 5XX                                              | \*/\*                                                 |

## get

Retrieves details about a specific return transaction, including its status, original transaction reference, and any associated metadata.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/returns/{uid}" method="get" path="/returns/{uid}" example="single_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetReturnsUidForbiddenException;
import com.newline53.sdk.models.errors.GetReturnsUidNotFoundException;
import com.newline53.sdk.models.operations.GetReturnsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetReturnsUidForbiddenException, GetReturnsUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetReturnsUidResponse res = sdk.returns().get()
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

**[GetReturnsUidResponse](../../models/operations/GetReturnsUidResponse.md)**

### Errors

| Error Type                                    | Status Code                                   | Content Type                                  |
| --------------------------------------------- | --------------------------------------------- | --------------------------------------------- |
| models/errors/GetReturnsUidForbiddenException | 403                                           | application/json                              |
| models/errors/GetReturnsUidNotFoundException  | 404                                           | application/json                              |
| models/errors/APIException                    | 4XX, 5XX                                      | \*/\*                                         |