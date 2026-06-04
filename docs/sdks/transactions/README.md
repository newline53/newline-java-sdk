# Transactions

## Overview

Transactions represent asset movements, such as ACH payments, wire transfers, or card purchases. Track transaction statuses and events through these endpoints.  
**Endpoints:**

- GET [List Transactions: GET /transactions](https://developers.newline53.com/reference/get_transactions)

- GET [Get a single Transaction: GET /transactions/{uid}](https://developers.newline53.com/reference/get_transactions-uid)

- PUT [Approve or deny a Transaction: PUT /transactions/{uid}/authorize](https://developers.newline53.com/reference/put_transactions-uid-authorize)

- GET [List Transaction Events: GET /transaction_events](https://developers.newline53.com/reference/get_transaction-events)

- GET [Get a single Transaction Event: GET /transaction_events/{uid}](https://developers.newline53.com/reference/get_transaction-events-uid)

- GET [List Synthetic Line Items: GET /synthetic_line_items](https://developers.newline53.com/reference/get_synthetic-line-items)

- GET [Get a single Synthetic Line Item: GET /synthetic_line_items/{uid}](https://developers.newline53.com/reference/get_synthetic-line-items-uid)

- GET [List Custodial Line Items: GET /custodial_line_items](https://developers.newline53.com/reference/get_custodial-line-items)

- GET [Get a single Custodial Line Item: GET /custodial_line_items/{uid}](https://developers.newline53.com/reference/get_custodial-line-items-uid)

Transactions are created based on how you instruct Newline to move assets (a Transfer) or how assets are moved or spent outside your application (ATM withdrawals, debit card purchases, wire transfers, etc…). The Transaction contains the amount, origin, and destination of assets. Newline categorizes Transactions into types to assist in their classification and representation.

Transactions fall into many categories, including debit card purchases, direct deposits, interest, and fees. This endpoint can retrieve a list of Transactions or track the status of an ongoing Transaction.

Transaction Events are created as a result of a Transaction. They capture the steps required to complete the Transaction. These can be used to view the progress of an in-flight Transaction or see the history of a completed Transaction.

Line Items are created for each Transaction Event. They catalog the individual credits and debits associated with the accounts involved in the Transaction.

### Available Operations

* [list](#list) - List Transactions
* [get](#get) - Get a single Transaction
* [authorize](#authorize) - Approve or deny a transaction
* [listEvents](#listevents) - List Transaction Events
* [getTransactionEvent](#gettransactionevent) - Get a single Transaction Event
* [listSyntheticLineItems](#listsyntheticlineitems) - List Synthetic Line Items
* [getSyntheticLineItem](#getsyntheticlineitem) - Get a single Synthetic Line Item
* [listCustodialLineItems](#listcustodiallineitems) - List Custodial Line Items
* [getCustodialLineItem](#getcustodiallineitem) - Get a single Custodial Line Item

## list

Retrieves a list of Transactions. Transactions representing expired authorizations or expired reversals are suppressed by default.

### Example Usage: transactions

<!-- UsageSnippet language="java" operationID="get_/transactions" method="get" path="/transactions" example="transactions" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsQueryParamType;
import com.newline53.sdk.models.operations.GetTransactionsRequest;
import com.newline53.sdk.models.operations.GetTransactionsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsRequest req = GetTransactionsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .poolUid("wTSMX1GubP21ev2h")
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .syntheticAccountUid("4XkJnsfHsuqrxmeX")
                .type(GetTransactionsQueryParamType.ACH)
                .hasReturn(true)
                .showDeniedAuths(true)
                .showExpired(true)
                .searchDescription("Transfer*")
                .includeZero(true)
                .createdAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .createdAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .initialActionAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .initialActionAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledIndexAfter(123L)
                .settledIndexBefore(123L)
                .idAfter(123L)
                .idBefore(123L)
                .build();

        GetTransactionsResponse res = sdk.transactions().list()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transactions_with_denials

<!-- UsageSnippet language="java" operationID="get_/transactions" method="get" path="/transactions" example="transactions_with_denials" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsQueryParamType;
import com.newline53.sdk.models.operations.GetTransactionsRequest;
import com.newline53.sdk.models.operations.GetTransactionsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsRequest req = GetTransactionsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .poolUid("wTSMX1GubP21ev2h")
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .syntheticAccountUid("4XkJnsfHsuqrxmeX")
                .type(GetTransactionsQueryParamType.ACH)
                .hasReturn(true)
                .showDeniedAuths(true)
                .showExpired(true)
                .searchDescription("Transfer*")
                .includeZero(true)
                .createdAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .createdAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .initialActionAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .initialActionAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .settledIndexAfter(123L)
                .settledIndexBefore(123L)
                .idAfter(123L)
                .idBefore(123L)
                .build();

        GetTransactionsResponse res = sdk.transactions().list()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [GetTransactionsRequest](../../models/operations/GetTransactionsRequest.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[GetTransactionsResponse](../../models/operations/GetTransactionsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## get

Retrieves a single Transaction resource along with its details, including amount, origin, destination, and status.

### Example Usage: ach_transaction

<!-- UsageSnippet language="java" operationID="get_/transactions/{uid}" method="get" path="/transactions/{uid}" example="ach_transaction" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsUidResponse res = sdk.transactions().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: initiated_ach_return

<!-- UsageSnippet language="java" operationID="get_/transactions/{uid}" method="get" path="/transactions/{uid}" example="initiated_ach_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsUidResponse res = sdk.transactions().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: initiated_wire_return

<!-- UsageSnippet language="java" operationID="get_/transactions/{uid}" method="get" path="/transactions/{uid}" example="initiated_wire_return" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsUidResponse res = sdk.transactions().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_transaction

<!-- UsageSnippet language="java" operationID="get_/transactions/{uid}" method="get" path="/transactions/{uid}" example="instant_payment_transaction" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsUidResponse res = sdk.transactions().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_transaction

<!-- UsageSnippet language="java" operationID="get_/transactions/{uid}" method="get" path="/transactions/{uid}" example="wire_transaction" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionsUidResponse res = sdk.transactions().get()
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

**[GetTransactionsUidResponse](../../models/operations/GetTransactionsUidResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## authorize

Approves or denies a pending Transaction. This endpoint is used to explicitly authorize or reject a Transaction before it is executed.

### Example Usage: default_routing_error

<!-- UsageSnippet language="java" operationID="put_/transactions/{uid}/authorize" method="put" path="/transactions/{uid}/authorize" example="default_routing_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException;
import com.newline53.sdk.models.operations.AuthorizationStatusRequest;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeRequestBody;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransactionsUidAuthorizeBadRequestException, PutTransactionsUidAuthorizeForbiddenException, PutTransactionsUidAuthorizeUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransactionsUidAuthorizeResponse res = sdk.transactions().authorize()
                .uid("<id>")
                .body(PutTransactionsUidAuthorizeRequestBody.builder()
                    .authorizationStatus(AuthorizationStatusRequest.CLIENT_DENIED)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transaction_authorization_decision_window_expired

<!-- UsageSnippet language="java" operationID="put_/transactions/{uid}/authorize" method="put" path="/transactions/{uid}/authorize" example="transaction_authorization_decision_window_expired" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException;
import com.newline53.sdk.models.operations.AuthorizationStatusRequest;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeRequestBody;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransactionsUidAuthorizeBadRequestException, PutTransactionsUidAuthorizeForbiddenException, PutTransactionsUidAuthorizeUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransactionsUidAuthorizeResponse res = sdk.transactions().authorize()
                .uid("<id>")
                .body(PutTransactionsUidAuthorizeRequestBody.builder()
                    .authorizationStatus(AuthorizationStatusRequest.CLIENT_DENIED)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transaction_authorization_error

<!-- UsageSnippet language="java" operationID="put_/transactions/{uid}/authorize" method="put" path="/transactions/{uid}/authorize" example="transaction_authorization_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException;
import com.newline53.sdk.models.operations.AuthorizationStatusRequest;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeRequestBody;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransactionsUidAuthorizeBadRequestException, PutTransactionsUidAuthorizeForbiddenException, PutTransactionsUidAuthorizeUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransactionsUidAuthorizeResponse res = sdk.transactions().authorize()
                .uid("<id>")
                .body(PutTransactionsUidAuthorizeRequestBody.builder()
                    .authorizationStatus(AuthorizationStatusRequest.CLIENT_DENIED)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transaction_authorization_status_error

<!-- UsageSnippet language="java" operationID="put_/transactions/{uid}/authorize" method="put" path="/transactions/{uid}/authorize" example="transaction_authorization_status_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException;
import com.newline53.sdk.models.operations.AuthorizationStatusRequest;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeRequestBody;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransactionsUidAuthorizeBadRequestException, PutTransactionsUidAuthorizeForbiddenException, PutTransactionsUidAuthorizeUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransactionsUidAuthorizeResponse res = sdk.transactions().authorize()
                .uid("<id>")
                .body(PutTransactionsUidAuthorizeRequestBody.builder()
                    .authorizationStatus(AuthorizationStatusRequest.CLIENT_DENIED)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transaction_ineligible_for_authorization

<!-- UsageSnippet language="java" operationID="put_/transactions/{uid}/authorize" method="put" path="/transactions/{uid}/authorize" example="transaction_ineligible_for_authorization" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeBadRequestException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeForbiddenException;
import com.newline53.sdk.models.errors.PutTransactionsUidAuthorizeUnprocessableEntityException;
import com.newline53.sdk.models.operations.AuthorizationStatusRequest;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeRequestBody;
import com.newline53.sdk.models.operations.PutTransactionsUidAuthorizeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransactionsUidAuthorizeBadRequestException, PutTransactionsUidAuthorizeForbiddenException, PutTransactionsUidAuthorizeUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransactionsUidAuthorizeResponse res = sdk.transactions().authorize()
                .uid("<id>")
                .body(PutTransactionsUidAuthorizeRequestBody.builder()
                    .authorizationStatus(AuthorizationStatusRequest.CLIENT_DENIED)
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                   | Type                                                                                                        | Required                                                                                                    | Description                                                                                                 |
| ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `uid`                                                                                                       | *String*                                                                                                    | :heavy_check_mark:                                                                                          | Newline-generated unique id resource specific to the current endpoint                                       |
| `body`                                                                                                      | [PutTransactionsUidAuthorizeRequestBody](../../models/operations/PutTransactionsUidAuthorizeRequestBody.md) | :heavy_check_mark:                                                                                          | N/A                                                                                                         |

### Response

**[PutTransactionsUidAuthorizeResponse](../../models/operations/PutTransactionsUidAuthorizeResponse.md)**

### Errors

| Error Type                                                            | Status Code                                                           | Content Type                                                          |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| models/errors/PutTransactionsUidAuthorizeBadRequestException          | 400                                                                   | application/json                                                      |
| models/errors/PutTransactionsUidAuthorizeForbiddenException           | 403                                                                   | application/json                                                      |
| models/errors/PutTransactionsUidAuthorizeUnprocessableEntityException | 422                                                                   | application/json                                                      |
| models/errors/APIException                                            | 4XX, 5XX                                                              | \*/\*                                                                 |

## listEvents

Retrieves a list of Transaction Events. Transaction Events represent the steps required to complete a Transaction and can be used to track its progress or review its history.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/transaction_events" method="get" path="/transaction_events" example="transactionEvents" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionEventsQueryParamType;
import com.newline53.sdk.models.operations.GetTransactionEventsRequest;
import com.newline53.sdk.models.operations.GetTransactionEventsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionEventsRequest req = GetTransactionEventsRequest.builder()
                .sourceCustodialAccountUid("dmRtw1xkS9ghrntB")
                .destinationCustodialAccountUid("W55zKgvAk3zkpGM3")
                .custodialAccountUid("dmRtw1xkS9ghrntB")
                .type(GetTransactionEventsQueryParamType.ODFI_ACH_DEPOSIT)
                .transactionUid("SMwKC1osz77DTEiu")
                .build();

        GetTransactionEventsResponse res = sdk.transactions().listEvents()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `request`                                                                             | [GetTransactionEventsRequest](../../models/operations/GetTransactionEventsRequest.md) | :heavy_check_mark:                                                                    | The request object to use for the request.                                            |

### Response

**[GetTransactionEventsResponse](../../models/operations/GetTransactionEventsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getTransactionEvent

Retrieves a single Transaction Event resource, including its status, timestamps, and associated Transaction details.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/transaction_events/{uid}" method="get" path="/transaction_events/{uid}" example="transactionEvent" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransactionEventsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransactionEventsUidResponse res = sdk.transactions().getTransactionEvent()
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

**[GetTransactionEventsUidResponse](../../models/operations/GetTransactionEventsUidResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listSyntheticLineItems

Retrieves a list of Synthetic Line Items. These represent individual debits and credits associated with Synthetic Accounts as part of a Transaction Event.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/synthetic_line_items" method="get" path="/synthetic_line_items" example="syntheticLineItems" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticLineItemsRequest;
import com.newline53.sdk.models.operations.GetSyntheticLineItemsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticLineItemsRequest req = GetSyntheticLineItemsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .poolUid("wTSMX1GubP21ev2h")
                .syntheticAccountUid("4XkJnsfHsuqrxmeX")
                .transactionUid("SMwKC1osz77DTEiu")
                .build();

        GetSyntheticLineItemsResponse res = sdk.transactions().listSyntheticLineItems()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `request`                                                                               | [GetSyntheticLineItemsRequest](../../models/operations/GetSyntheticLineItemsRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[GetSyntheticLineItemsResponse](../../models/operations/GetSyntheticLineItemsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getSyntheticLineItem

Retrieves a single Synthetic Line Item resource, including the amount, account, and associated Transaction Event.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/synthetic_line_items/{uid}" method="get" path="/synthetic_line_items/{uid}" example="syntheticLineItem" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticLineItemsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticLineItemsUidResponse res = sdk.transactions().getSyntheticLineItem()
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

**[GetSyntheticLineItemsUidResponse](../../models/operations/GetSyntheticLineItemsUidResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listCustodialLineItems

Retrieves a list of Custodial Line Items. These represent individual debits and credits associated with Custodial Accounts as part of a Transaction Event.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/custodial_line_items" method="get" path="/custodial_line_items" example="custodialLineItems" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetCustodialLineItemsRequest;
import com.newline53.sdk.models.operations.GetCustodialLineItemsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCustodialLineItemsRequest req = GetCustodialLineItemsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .custodialAccountUid("dmRtw1xkS9ghrntB")
                .transactionEventUid("MB2yqBrm3c4bUbou")
                .transactionUid("SMwKC1osz77DTEiu")
                .build();

        GetCustodialLineItemsResponse res = sdk.transactions().listCustodialLineItems()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `request`                                                                               | [GetCustodialLineItemsRequest](../../models/operations/GetCustodialLineItemsRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[GetCustodialLineItemsResponse](../../models/operations/GetCustodialLineItemsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getCustodialLineItem

Retrieves a single Custodial Line Item resource, including the amount, account, and associated Transaction Event.

### Example Usage

<!-- UsageSnippet language="java" operationID="get_/custodial_line_items/{uid}" method="get" path="/custodial_line_items/{uid}" example="custodialLineItem" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetCustodialLineItemsUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCustodialLineItemsUidResponse res = sdk.transactions().getCustodialLineItem()
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

**[GetCustodialLineItemsUidResponse](../../models/operations/GetCustodialLineItemsUidResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |