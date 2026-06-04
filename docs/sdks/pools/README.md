# Pools

## Overview

Pools group multiple Customers for asset sharing and distributed account ownership. Each Pool is unique to a Customer or program.

**Endpoints:**

- GET [List Pools: GET /pools](https://developers.newline53.com/reference/get_pools)
    
- GET [Get a single Pool: GET /pools/{uid}](https://developers.newline53.com/reference/get_pools-uid)

A pool is a construct that Newline uses to associate multiple customers with each other. A Pool is always associated with at least one Customer, but all accounts are only ever associated with a single Pool. This enables asset sharing and distributed ownership of accounts across multiple Customers.

Newline currently supports single Customer Pools, where **one Customer is associated with one Pool and vice versa**. All accounts, transfers, and transactions are related to the Customer's Pool, not the Customer. The Pool UID appears on several endpoint responses and may be required by the API in some instances.

### Available Operations

* [list](#list) - List Pools
* [get](#get) - Get a single Pool

## list

Retrieves a list of Pools filtered by the given parameters.


### Example Usage

<!-- UsageSnippet language="java" operationID="listPools" method="get" path="/pools" example="pool_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListPoolsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListPoolsResponse res = sdk.pools().list()
                .customerUid("uKxmLxUEiSj5h4M3")
                .limit(100L)
                .offset(0L)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `customerUid`                                                                                                             | @Nullable *String*                                                                                                        | :heavy_minus_sign:                                                                                                        | Filter by Customer. Multiple values are allowed, e.g. `customer_uid[]=uKxmLxUEiSj5h4M3&customer_uid[]=y9reyPMNEWuuYSC1`.<br/> | uKxmLxUEiSj5h4M3                                                                                                          |
| `limit`                                                                                                                   | @Nullable *long*                                                                                                          | :heavy_minus_sign:                                                                                                        | Maximum number of items to retrieve. This filter is automatically applied with the default value if not given.<br/>       |                                                                                                                           |
| `offset`                                                                                                                  | @Nullable *long*                                                                                                          | :heavy_minus_sign:                                                                                                        | Index of the items to start retrieving from                                                                               |                                                                                                                           |

### Response

**[ListPoolsResponse](../../models/operations/ListPoolsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## get

Retrieve overall status about a Pool as well as its total Asset Balances across all associated accounts.

### Example Usage

<!-- UsageSnippet language="java" operationID="getPool" method="get" path="/pools/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetPoolResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetPoolResponse res = sdk.pools().get()
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

**[GetPoolResponse](../../models/operations/GetPoolResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |