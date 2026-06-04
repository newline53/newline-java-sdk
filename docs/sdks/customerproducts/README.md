# CustomerProducts

## Overview

### Available Operations

* [list](#list) - List Customer Products
* [onboard](#onboard) - Onboard Customer onto a Product
* [get](#get) - Get a single Customer Product

## list

List Customers and the Products they have onboarded onto, filtered by the given parameters.


### Example Usage

<!-- UsageSnippet language="java" operationID="listCustomerProducts" method="get" path="/customer_products" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListCustomerProductsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListCustomerProductsResponse res = sdk.customerProducts().list()
                .programUid("pQtTCSXz57fuefzp")
                .productUid("zbJbEa72eKMgbbBv")
                .customerUid("uKxmLxUEiSj5h4M3")
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
| `programUid`                                                                                                              | @Nullable *String*                                                                                                        | :heavy_minus_sign:                                                                                                        | Only return Customer Products belonging to the submitted Program                                                          | pQtTCSXz57fuefzp                                                                                                          |
| `productUid`                                                                                                              | @Nullable *String*                                                                                                        | :heavy_minus_sign:                                                                                                        | Only return Customer Products belonging to the submitted Product                                                          | zbJbEa72eKMgbbBv                                                                                                          |
| `customerUid`                                                                                                             | @Nullable *String*                                                                                                        | :heavy_minus_sign:                                                                                                        | Filter by Customer. Multiple values are allowed, e.g. `customer_uid[]=uKxmLxUEiSj5h4M3&customer_uid[]=y9reyPMNEWuuYSC1`.<br/> | uKxmLxUEiSj5h4M3                                                                                                          |

### Response

**[ListCustomerProductsResponse](../../models/operations/ListCustomerProductsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## onboard

Submit a request to onboard a Customer onto a new product. Ensure all required Customer details have been provided with [Adjust Customer Data](https://developers.newline53.com/reference/put_customers-uid) before making this request. An error will be returned if details are missing or invalid.

The request to onboard a Customer serves as explicit confirmation from you that the Customer is ready for account opening. This event initiates the KYC/AML verification process and account opening in your Program. This is a billable event and is isolated intentionally for you to confirm that the Customer record is complete. Customer Onboarding is the event that locks the customer PII and profile responses from further edits.

Customer Onboarding is designed to work as follows:

- The Customer provides complete PII as defined by the Customers endpoint
- The Customer provides complete Customer Profile information as defined by the Product endpoint
- You submit a request to Customer Products with the specified Product this Customer is onboarding onto
- Newline performs a validation on the PII provided and the Customer Profile data to confirm all are valid

If the Customer passes these validations and meets the duration requirements described below, the Customer record is submitted to the KYC process

### Example Usage

<!-- UsageSnippet language="java" operationID="onboardCustomerProduct" method="post" path="/customer_products" example="missing-details" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.OnboardCustomerProductUnprocessableEntityException;
import com.newline53.sdk.models.operations.OnboardCustomerProductRequest;
import com.newline53.sdk.models.operations.OnboardCustomerProductResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws OnboardCustomerProductUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

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
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `request`                                                                                 | [OnboardCustomerProductRequest](../../models/operations/OnboardCustomerProductRequest.md) | :heavy_check_mark:                                                                        | The request object to use for the request.                                                |

### Response

**[OnboardCustomerProductResponse](../../models/operations/OnboardCustomerProductResponse.md)**

### Errors

| Error Type                                                       | Status Code                                                      | Content Type                                                     |
| ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- |
| models/errors/OnboardCustomerProductUnprocessableEntityException | 422                                                              | application/json                                                 |
| models/errors/APIException                                       | 4XX, 5XX                                                         | \*/\*                                                            |

## get

Retrieves a single Customer Product resource along with its associated Customer and Product

### Example Usage

<!-- UsageSnippet language="java" operationID="getCustomerProduct" method="get" path="/customer_products/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetCustomerProductResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCustomerProductResponse res = sdk.customerProducts().get()
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

**[GetCustomerProductResponse](../../models/operations/GetCustomerProductResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |