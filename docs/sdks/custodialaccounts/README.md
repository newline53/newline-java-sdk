# CustodialAccounts

## Overview

### Available Operations

* [list](#list) - List Custodial Accounts
* [get](#get) - Get a single Custodial Account
* [listClosingBalances](#listclosingbalances) - List Custodial Account Closing Balances
* [getClosingBalance](#getclosingbalance) - Get a single Custodial Account Closing Balance

## list

Retrieve a list of Custodial Accounts held by Fifth Third Bank for onboarded Customers. This endpoint returns active and archived accounts along with their balances.


### Example Usage

<!-- UsageSnippet language="java" operationID="listCustodialAccounts" method="get" path="/custodial_accounts" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListCustodialAccountsQueryParamType;
import com.newline53.sdk.models.operations.ListCustodialAccountsRequest;
import com.newline53.sdk.models.operations.ListCustodialAccountsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListCustodialAccountsRequest req = ListCustodialAccountsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .externalUid("client-generated-id")
                .type(ListCustodialAccountsQueryParamType.DDA)
                .build();

        ListCustodialAccountsResponse res = sdk.custodialAccounts().list()
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
| `request`                                                                               | [ListCustodialAccountsRequest](../../models/operations/ListCustodialAccountsRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[ListCustodialAccountsResponse](../../models/operations/ListCustodialAccountsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## get

Returns a single Custodial Account resource along with supporting details and account balances.


### Example Usage

<!-- UsageSnippet language="java" operationID="getCustodialAccount" method="get" path="/custodial_accounts/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetCustodialAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCustodialAccountResponse res = sdk.custodialAccounts().get()
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

**[GetCustodialAccountResponse](../../models/operations/GetCustodialAccountResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listClosingBalances

Retrieves a paginated list of Custodial Account Closing balances, filtered by various parameters.

### Example Usage

<!-- UsageSnippet language="java" operationID="listCustodialAccountClosingBalances" method="get" path="/custodial_account_closing_balances" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListCustodialAccountClosingBalancesRequest;
import com.newline53.sdk.models.operations.ListCustodialAccountClosingBalancesResponse;
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

        ListCustodialAccountClosingBalancesRequest req = ListCustodialAccountClosingBalancesRequest.builder()
                .custodialAccountUid("yqyYk5b1xgXFFrXs")
                .custodialAccountExternalUid("4XkJnsfHsuqrxmeX")
                .netUsdClosingBalanceAsOf(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .netUsdClosingBalanceBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .netUsdClosingBalanceAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .build();

        ListCustodialAccountClosingBalancesResponse res = sdk.custodialAccounts().listClosingBalances()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                           | Type                                                                                                                | Required                                                                                                            | Description                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                           | [ListCustodialAccountClosingBalancesRequest](../../models/operations/ListCustodialAccountClosingBalancesRequest.md) | :heavy_check_mark:                                                                                                  | The request object to use for the request.                                                                          |

### Response

**[ListCustodialAccountClosingBalancesResponse](../../models/operations/ListCustodialAccountClosingBalancesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getClosingBalance

Get a single Custodial Account Closing Balance

### Example Usage

<!-- UsageSnippet language="java" operationID="getCustodialAccountClosingBalance" method="get" path="/custodial_account_closing_balances/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetCustodialAccountClosingBalanceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCustodialAccountClosingBalanceResponse res = sdk.custodialAccounts().getClosingBalance()
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

**[GetCustodialAccountClosingBalanceResponse](../../models/operations/GetCustodialAccountClosingBalanceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |