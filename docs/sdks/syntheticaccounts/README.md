# SyntheticAccounts

## Overview

### Available Operations

* [listTypes](#listtypes) - List Synthetic Account Types
* [getType](#gettype) - Get a Single Synthetic Account Type
* [list](#list) - List Synthetic Accounts
* [create](#create) - Create a New Synthetic Account
* [retrieve](#retrieve) - Get a single Synthetic Account
* [update](#update) - Update the Synthetic Account metadata
* [delete](#delete) - Archive a Synthetic Account
* [getClosingBalances](#getclosingbalances) - List Synthetic Account Closing Balances

## listTypes

Retrieve a list of Synthetic Account Types available for use in your Program. These types define the behavior and characteristics of Synthetic Accounts.

### Example Usage

<!-- UsageSnippet language="java" operationID="listSyntheticAccountTypes" method="get" path="/synthetic_account_types" example="synthetic_account_types_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListSyntheticAccountTypesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListSyntheticAccountTypesResponse res = sdk.syntheticAccounts().listTypes()
                .programUid("EhrQZJNjCd79LLYq")
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

| Parameter                                                                                                       | Type                                                                                                            | Required                                                                                                        | Description                                                                                                     | Example                                                                                                         |
| --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `programUid`                                                                                                    | @Nullable *String*                                                                                              | :heavy_minus_sign:                                                                                              | Only list Synthetic Account Types that are available to be used by the given Program                            | EhrQZJNjCd79LLYq                                                                                                |
| `limit`                                                                                                         | @Nullable *long*                                                                                                | :heavy_minus_sign:                                                                                              | Maximum number of items to retrieve. This filter is automatically applied with the default value if not given.<br/> |                                                                                                                 |
| `offset`                                                                                                        | @Nullable *long*                                                                                                | :heavy_minus_sign:                                                                                              | Index of the items to start retrieving from                                                                     |                                                                                                                 |

### Response

**[ListSyntheticAccountTypesResponse](../../models/operations/ListSyntheticAccountTypesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getType

Returns a single Synthetic Account Type resource along with supporting details.

Enables changes to the Synthetic Account fields, including the Master Synthetic Account. The Master Synthetic Account remains identifiable by the `master_account` flag stored with the Synthetic Account record.

### Example Usage

<!-- UsageSnippet language="java" operationID="getSyntheticAccountType" method="get" path="/synthetic_account_types/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticAccountTypeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticAccountTypeResponse res = sdk.syntheticAccounts().getType()
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

**[GetSyntheticAccountTypeResponse](../../models/operations/GetSyntheticAccountTypeResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## list

Retrieve a list of Synthetic Accounts associated with the specified Customer and Pool. This endpoint supports filtering by account type, category, status, and sorting by balance or name.


### Example Usage

<!-- UsageSnippet language="java" operationID="listSyntheticAccounts" method="get" path="/synthetic_accounts" example="synthetic_accounts_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListSyntheticAccountsQueryParamStatus;
import com.newline53.sdk.models.operations.ListSyntheticAccountsRequest;
import com.newline53.sdk.models.operations.ListSyntheticAccountsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListSyntheticAccountsRequest req = ListSyntheticAccountsRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .externalUid("client-generated-id")
                .poolUid("wTSMX1GubP21ev2h")
                .syntheticAccountTypeUid("q4mdMxMtjXfdbrjn")
                .status(ListSyntheticAccountsQueryParamStatus.ACTIVE)
                .build();

        ListSyntheticAccountsResponse res = sdk.syntheticAccounts().list()
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
| `request`                                                                               | [ListSyntheticAccountsRequest](../../models/operations/ListSyntheticAccountsRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[ListSyntheticAccountsResponse](../../models/operations/ListSyntheticAccountsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## create

Create a new Synthetic Account in the Pool with the provided specification.

External Synthetic Accounts are counterparty records that represent accounts at external financial institutions. They contain all the necessary information to execute a payment. For specifics about each payment rail's requirements, refer to our [Payment Rails](https://developers.newline53.com/docs/payment-rails) guides.

### Example Usage: ach_account

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="ach_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("New Resource Name")
                .poolUid("kaxHFJnWvJxRJZxq")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .routingNumber("123456789")
                .accountNumber("123456789012")
                .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                .ach(CreateSyntheticAccountAchRequest.builder()
                    .accountType(CreateSyntheticAccountAccountTypeRequest.CHECKING)
                    .counterpartyName("Thelma's Flooring LLC")
                    .build())
                .instantPayment(CreateSyntheticAccountInstantPaymentRequest.builder()
                    .counterpartyAddress(CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .street2("Suite 4A")
                        .build())
                    .counterpartyName("Marge's Roofing Inc")
                    .email("payments@veryexcellentbusiness.com")
                    .phone("5555551212")
                    .build())
                .wire(CreateSyntheticAccountWireRequest.builder()
                    .counterpartyName("Marge's Roofing Inc")
                    .counterpartyAddress(CreateSyntheticAccountWireCounterpartyAddressRequest.builder()
                        .line1("234 Xyz Rd")
                        .line2("APT 5")
                        .line3("Boston, MA 02110")
                        .country("US")
                        .build())
                    .counterpartyBankAddress(CreateSyntheticAccountCounterpartyBankAddressRequest.builder()
                        .line1("123 Abc St.")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .country(null)
                        .build())
                    .counterpartyBankName("East West Regional Bank")
                    .build())
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: general_synthetic_account

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="general_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("New Resource Name")
                .poolUid("kaxHFJnWvJxRJZxq")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .routingNumber("123456789")
                .accountNumber("123456789012")
                .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                .ach(CreateSyntheticAccountAchRequest.builder()
                    .accountType(CreateSyntheticAccountAccountTypeRequest.CHECKING)
                    .counterpartyName("Thelma's Flooring LLC")
                    .build())
                .instantPayment(CreateSyntheticAccountInstantPaymentRequest.builder()
                    .counterpartyAddress(CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .street2("Suite 4A")
                        .build())
                    .counterpartyName("Marge's Roofing Inc")
                    .email("payments@veryexcellentbusiness.com")
                    .phone("5555551212")
                    .build())
                .wire(CreateSyntheticAccountWireRequest.builder()
                    .counterpartyName("Marge's Roofing Inc")
                    .counterpartyAddress(CreateSyntheticAccountWireCounterpartyAddressRequest.builder()
                        .line1("234 Xyz Rd")
                        .line2("APT 5")
                        .line3("Boston, MA 02110")
                        .country("US")
                        .build())
                    .counterpartyBankAddress(CreateSyntheticAccountCounterpartyBankAddressRequest.builder()
                        .line1("123 Abc St.")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .country(null)
                        .build())
                    .counterpartyBankName("East West Regional Bank")
                    .build())
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_synthetic_account

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="instant_payment_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("New Resource Name")
                .poolUid("kaxHFJnWvJxRJZxq")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .routingNumber("123456789")
                .accountNumber("123456789012")
                .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                .ach(CreateSyntheticAccountAchRequest.builder()
                    .accountType(CreateSyntheticAccountAccountTypeRequest.CHECKING)
                    .counterpartyName("Thelma's Flooring LLC")
                    .build())
                .instantPayment(CreateSyntheticAccountInstantPaymentRequest.builder()
                    .counterpartyAddress(CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .street2("Suite 4A")
                        .build())
                    .counterpartyName("Marge's Roofing Inc")
                    .email("payments@veryexcellentbusiness.com")
                    .phone("5555551212")
                    .build())
                .wire(CreateSyntheticAccountWireRequest.builder()
                    .counterpartyName("Marge's Roofing Inc")
                    .counterpartyAddress(CreateSyntheticAccountWireCounterpartyAddressRequest.builder()
                        .line1("234 Xyz Rd")
                        .line2("APT 5")
                        .line3("Boston, MA 02110")
                        .country("US")
                        .build())
                    .counterpartyBankAddress(CreateSyntheticAccountCounterpartyBankAddressRequest.builder()
                        .line1("123 Abc St.")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .country(null)
                        .build())
                    .counterpartyBankName("East West Regional Bank")
                    .build())
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: new_synthetic_account

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="new_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("Spinach Fund")
                .poolUid("wTSMX1GubP21ev2h")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: post_error_response

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="post_error_response" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("New Resource Name")
                .poolUid("kaxHFJnWvJxRJZxq")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .routingNumber("123456789")
                .accountNumber("123456789012")
                .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                .ach(CreateSyntheticAccountAchRequest.builder()
                    .accountType(CreateSyntheticAccountAccountTypeRequest.CHECKING)
                    .counterpartyName("Thelma's Flooring LLC")
                    .build())
                .instantPayment(CreateSyntheticAccountInstantPaymentRequest.builder()
                    .counterpartyAddress(CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .street2("Suite 4A")
                        .build())
                    .counterpartyName("Marge's Roofing Inc")
                    .email("payments@veryexcellentbusiness.com")
                    .phone("5555551212")
                    .build())
                .wire(CreateSyntheticAccountWireRequest.builder()
                    .counterpartyName("Marge's Roofing Inc")
                    .counterpartyAddress(CreateSyntheticAccountWireCounterpartyAddressRequest.builder()
                        .line1("234 Xyz Rd")
                        .line2("APT 5")
                        .line3("Boston, MA 02110")
                        .country("US")
                        .build())
                    .counterpartyBankAddress(CreateSyntheticAccountCounterpartyBankAddressRequest.builder()
                        .line1("123 Abc St.")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .country(null)
                        .build())
                    .counterpartyBankName("East West Regional Bank")
                    .build())
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_synthetic_account

<!-- UsageSnippet language="java" operationID="createSyntheticAccount" method="post" path="/synthetic_accounts" example="wire_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.ConflictException;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.CreateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws ConflictException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateSyntheticAccountRequest req = CreateSyntheticAccountRequest.builder()
                .externalUid("partner-generated-id")
                .name("New Resource Name")
                .poolUid("kaxHFJnWvJxRJZxq")
                .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                .routingNumber("123456789")
                .accountNumber("123456789012")
                .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                .ach(CreateSyntheticAccountAchRequest.builder()
                    .accountType(CreateSyntheticAccountAccountTypeRequest.CHECKING)
                    .counterpartyName("Thelma's Flooring LLC")
                    .build())
                .instantPayment(CreateSyntheticAccountInstantPaymentRequest.builder()
                    .counterpartyAddress(CreateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .street2("Suite 4A")
                        .build())
                    .counterpartyName("Marge's Roofing Inc")
                    .email("payments@veryexcellentbusiness.com")
                    .phone("5555551212")
                    .build())
                .wire(CreateSyntheticAccountWireRequest.builder()
                    .counterpartyName("Marge's Roofing Inc")
                    .counterpartyAddress(CreateSyntheticAccountWireCounterpartyAddressRequest.builder()
                        .line1("234 Xyz Rd")
                        .line2("APT 5")
                        .line3("Boston, MA 02110")
                        .country("US")
                        .build())
                    .counterpartyBankAddress(CreateSyntheticAccountCounterpartyBankAddressRequest.builder()
                        .line1("123 Abc St.")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .country(null)
                        .build())
                    .counterpartyBankName("East West Regional Bank")
                    .build())
                .build();

        CreateSyntheticAccountResponse res = sdk.syntheticAccounts().create()
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
| `request`                                                                                 | [CreateSyntheticAccountRequest](../../models/operations/CreateSyntheticAccountRequest.md) | :heavy_check_mark:                                                                        | The request object to use for the request.                                                |

### Response

**[CreateSyntheticAccountResponse](../../models/operations/CreateSyntheticAccountResponse.md)**

### Errors

| Error Type                      | Status Code                     | Content Type                    |
| ------------------------------- | ------------------------------- | ------------------------------- |
| models/errors/ConflictException | 409                             | application/json                |
| models/errors/APIException      | 4XX, 5XX                        | \*/\*                           |

## retrieve

Returns a single Synthetic Account resource along with supporting details and account balances.

Note: Newline will suppress the `account_number` value for Synthetic Accounts in the `ach_external`, `wire_external`, and `instant_payments_external` categories. The `account_number_last_four` value will be returned in the response to help identify these Synthetic Accounts.

### Example Usage: ach_account

<!-- UsageSnippet language="java" operationID="getSyntheticAccount" method="get" path="/synthetic_accounts/{uid}" example="ach_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticAccountResponse res = sdk.syntheticAccounts().retrieve()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: general_synthetic_account

<!-- UsageSnippet language="java" operationID="getSyntheticAccount" method="get" path="/synthetic_accounts/{uid}" example="general_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticAccountResponse res = sdk.syntheticAccounts().retrieve()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_synthetic_account

<!-- UsageSnippet language="java" operationID="getSyntheticAccount" method="get" path="/synthetic_accounts/{uid}" example="instant_payment_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticAccountResponse res = sdk.syntheticAccounts().retrieve()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_synthetic_account

<!-- UsageSnippet language="java" operationID="getSyntheticAccount" method="get" path="/synthetic_accounts/{uid}" example="wire_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetSyntheticAccountResponse res = sdk.syntheticAccounts().retrieve()
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

**[GetSyntheticAccountResponse](../../models/operations/GetSyntheticAccountResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## update

Enables changes to the Synthetic Account fields, including the Master Synthetic Account. The Master Synthetic Account remains identifiable by the `master_account` flag stored with the Synthetic Account record.

### Example Usage: ach_account

<!-- UsageSnippet language="java" operationID="updateSyntheticAccount" method="put" path="/synthetic_accounts/{uid}" example="ach_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountRequestBody;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws UpdateSyntheticAccountBadRequestException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        UpdateSyntheticAccountResponse res = sdk.syntheticAccounts().update()
                .uid("<id>")
                .body(UpdateSyntheticAccountRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .ach(UpdateSyntheticAccountAchRequest.builder()
                        .accountType(UpdateSyntheticAccountAccountTypeRequest.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(UpdateSyntheticAccountInstantPaymentRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                            .streetNumber("123abc")
                            .street1("Abc St.")
                            .city("Chicago")
                            .state("IL")
                            .postalCode("60301")
                            .country(null)
                            .street2("Suite 4A")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .email("payments@veryexcellentbusiness.com")
                        .phone("5555551212")
                        .build())
                    .wire(UpdateSyntheticAccountWireRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountWireCounterpartyAddressRequest.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyBankAddress(UpdateSyntheticAccountCounterpartyBankAddressRequest.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: general_synthetic_account

<!-- UsageSnippet language="java" operationID="updateSyntheticAccount" method="put" path="/synthetic_accounts/{uid}" example="general_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountRequestBody;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws UpdateSyntheticAccountBadRequestException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        UpdateSyntheticAccountResponse res = sdk.syntheticAccounts().update()
                .uid("<id>")
                .body(UpdateSyntheticAccountRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .ach(UpdateSyntheticAccountAchRequest.builder()
                        .accountType(UpdateSyntheticAccountAccountTypeRequest.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(UpdateSyntheticAccountInstantPaymentRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                            .streetNumber("123abc")
                            .street1("Abc St.")
                            .city("Chicago")
                            .state("IL")
                            .postalCode("60301")
                            .country(null)
                            .street2("Suite 4A")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .email("payments@veryexcellentbusiness.com")
                        .phone("5555551212")
                        .build())
                    .wire(UpdateSyntheticAccountWireRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountWireCounterpartyAddressRequest.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyBankAddress(UpdateSyntheticAccountCounterpartyBankAddressRequest.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_synthetic_account

<!-- UsageSnippet language="java" operationID="updateSyntheticAccount" method="put" path="/synthetic_accounts/{uid}" example="instant_payment_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountRequestBody;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws UpdateSyntheticAccountBadRequestException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        UpdateSyntheticAccountResponse res = sdk.syntheticAccounts().update()
                .uid("<id>")
                .body(UpdateSyntheticAccountRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .ach(UpdateSyntheticAccountAchRequest.builder()
                        .accountType(UpdateSyntheticAccountAccountTypeRequest.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(UpdateSyntheticAccountInstantPaymentRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                            .streetNumber("123abc")
                            .street1("Abc St.")
                            .city("Chicago")
                            .state("IL")
                            .postalCode("60301")
                            .country(null)
                            .street2("Suite 4A")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .email("payments@veryexcellentbusiness.com")
                        .phone("5555551212")
                        .build())
                    .wire(UpdateSyntheticAccountWireRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountWireCounterpartyAddressRequest.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyBankAddress(UpdateSyntheticAccountCounterpartyBankAddressRequest.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: missing_param_error

<!-- UsageSnippet language="java" operationID="updateSyntheticAccount" method="put" path="/synthetic_accounts/{uid}" example="missing_param_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountRequestBody;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws UpdateSyntheticAccountBadRequestException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        UpdateSyntheticAccountResponse res = sdk.syntheticAccounts().update()
                .uid("<id>")
                .body(UpdateSyntheticAccountRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .ach(UpdateSyntheticAccountAchRequest.builder()
                        .accountType(UpdateSyntheticAccountAccountTypeRequest.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(UpdateSyntheticAccountInstantPaymentRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                            .streetNumber("123abc")
                            .street1("Abc St.")
                            .city("Chicago")
                            .state("IL")
                            .postalCode("60301")
                            .country(null)
                            .street2("Suite 4A")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .email("payments@veryexcellentbusiness.com")
                        .phone("5555551212")
                        .build())
                    .wire(UpdateSyntheticAccountWireRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountWireCounterpartyAddressRequest.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyBankAddress(UpdateSyntheticAccountCounterpartyBankAddressRequest.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_synthetic_account

<!-- UsageSnippet language="java" operationID="updateSyntheticAccount" method="put" path="/synthetic_accounts/{uid}" example="wire_synthetic_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.UpdateSyntheticAccountBadRequestException;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAccountTypeRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountAchRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyBankAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountInstantPaymentRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountRequestBody;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountResponse;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireCounterpartyAddressRequest;
import com.newline53.sdk.models.operations.UpdateSyntheticAccountWireRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws UpdateSyntheticAccountBadRequestException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        UpdateSyntheticAccountResponse res = sdk.syntheticAccounts().update()
                .uid("<id>")
                .body(UpdateSyntheticAccountRequestBody.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .ach(UpdateSyntheticAccountAchRequest.builder()
                        .accountType(UpdateSyntheticAccountAccountTypeRequest.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(UpdateSyntheticAccountInstantPaymentRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountInstantPaymentCounterpartyAddressRequest.builder()
                            .streetNumber("123abc")
                            .street1("Abc St.")
                            .city("Chicago")
                            .state("IL")
                            .postalCode("60301")
                            .country(null)
                            .street2("Suite 4A")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .email("payments@veryexcellentbusiness.com")
                        .phone("5555551212")
                        .build())
                    .wire(UpdateSyntheticAccountWireRequest.builder()
                        .counterpartyAddress(UpdateSyntheticAccountWireCounterpartyAddressRequest.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyBankAddress(UpdateSyntheticAccountCounterpartyBankAddressRequest.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `uid`                                                                                             | *String*                                                                                          | :heavy_check_mark:                                                                                | Newline-generated unique id resource specific to the current endpoint                             |
| `body`                                                                                            | [UpdateSyntheticAccountRequestBody](../../models/operations/UpdateSyntheticAccountRequestBody.md) | :heavy_check_mark:                                                                                | N/A                                                                                               |

### Response

**[UpdateSyntheticAccountResponse](../../models/operations/UpdateSyntheticAccountResponse.md)**

### Errors

| Error Type                                              | Status Code                                             | Content Type                                            |
| ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- |
| models/errors/UpdateSyntheticAccountBadRequestException | 400                                                     | application/json                                        |
| models/errors/APIException                              | 4XX, 5XX                                                | \*/\*                                                   |

## delete

In order to archive a Synthetic Account, the account must:

- not be a Master Synthetic Account i.e. `master_account` must be false.
- have zero balance.
- have no pending Transfers.

Master Synthetic Accounts are archived when the Program Customer is archived ([DELETE /customers/:uid](https://developers.newline53.com/reference/delete_customers-uid)).

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteSyntheticAccount" method="delete" path="/synthetic_accounts/{uid}" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.DeleteSyntheticAccountUnprocessableEntityException;
import com.newline53.sdk.models.operations.DeleteSyntheticAccountResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws DeleteSyntheticAccountUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        DeleteSyntheticAccountResponse res = sdk.syntheticAccounts().delete()
                .uid("<id>")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `uid`                                                                 | *String*                                                              | :heavy_check_mark:                                                    | Newline-generated unique id resource specific to the current endpoint |

### Response

**[DeleteSyntheticAccountResponse](../../models/operations/DeleteSyntheticAccountResponse.md)**

### Errors

| Error Type                                                       | Status Code                                                      | Content Type                                                     |
| ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- |
| models/errors/DeleteSyntheticAccountUnprocessableEntityException | 422                                                              | application/json                                                 |
| models/errors/APIException                                       | 4XX, 5XX                                                         | \*/\*                                                            |

## getClosingBalances

Retrieves a paginated list of Synthetic Account Closing balances, filtered by various parameters.

### Example Usage

<!-- UsageSnippet language="java" operationID="listSyntheticAccountClosingBalances" method="get" path="/synthetic_account_closing_balances" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListSyntheticAccountClosingBalancesRequest;
import com.newline53.sdk.models.operations.ListSyntheticAccountClosingBalancesResponse;
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

        ListSyntheticAccountClosingBalancesRequest req = ListSyntheticAccountClosingBalancesRequest.builder()
                .syntheticAccountUid("4XkJnsfHsuqrxmeX")
                .syntheticAccountExternalUid("4XkJnsfHsuqrxmeX")
                .netUsdClosingBalanceAsOf(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .netUsdClosingBalanceBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .netUsdClosingBalanceAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .build();

        ListSyntheticAccountClosingBalancesResponse res = sdk.syntheticAccounts().getClosingBalances()
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
| `request`                                                                                                           | [ListSyntheticAccountClosingBalancesRequest](../../models/operations/ListSyntheticAccountClosingBalancesRequest.md) | :heavy_check_mark:                                                                                                  | The request object to use for the request.                                                                          |

### Response

**[ListSyntheticAccountClosingBalancesResponse](../../models/operations/ListSyntheticAccountClosingBalancesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |