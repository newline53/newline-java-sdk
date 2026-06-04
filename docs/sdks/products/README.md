# Products

## Overview

Products represent financial services available in your program. Discover onboarding requirements, prerequisites, and detailed product configurations.

**Endpoints:**

- GET [List Products: GET /products](https://developers.newline53.com/reference/get_products)
    
- GET [Get a single Product: GET /products/{uid}](https://developers.newline53.com/reference/get_products-uid)

The Products endpoint represents the financial products available to your Customers. It exposes the accounts, compliance, and Customer Profile Requirements necessary for your Customer to access the Product. For some programs, a customer must onboard one product before another product is made available. Newline will work with clients to define the required Product onboarding sequence as part of your Program.

Use the Products endpoint to view the Products available to your Program and the prerequisite information or actions that must be taken for a Customer to access the Product.

For more information about Newline's products and their utilization, please refer to the [Products Programs](https://developers.newline53.com/docs/product-programs) guides.

### Available Operations

* [list](#list) - List Products
* [get](#get) - Get a single Product

## list

Products filtered by the given parameters.


### Example Usage

<!-- UsageSnippet language="java" operationID="listProducts" method="get" path="/products" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListProductsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListProductsResponse res = sdk.products().list()
                .programUid("pQtTCSXz57fuefzp")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                               | Type                                                    | Required                                                | Description                                             | Example                                                 |
| ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- |
| `programUid`                                            | @Nullable *String*                                      | :heavy_minus_sign:                                      | Only return Products belonging to the submitted Program | pQtTCSXz57fuefzp                                        |

### Response

**[ListProductsResponse](../../models/operations/ListProductsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## get

Retrieve overall status about a Product as well as its configuration, availability, and any associated metadata.

### Example Usage

<!-- UsageSnippet language="java" operationID="getProduct" method="get" path="/products/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetProductResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetProductResponse res = sdk.products().get()
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

**[GetProductResponse](../../models/operations/GetProductResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |