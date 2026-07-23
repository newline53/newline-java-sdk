# Transfers

## Overview

Transfers facilitate the movement of assets between accounts, enabling transactions such as payments and withdrawals.

**Endpoints:**

- GET [List Transfers: GET /transfers](https://developers.newline53.com/reference/get_transfers)

- POST [Initiate a Transfer: POST /transfers](https://developers.newline53.com/reference/post_transfers)

- GET [Get a single Transfer: GET /transfers/{uid}](https://developers.newline53.com/reference/get_transfers-uid)

- PUT [Cancel a Transfer: PUT /transfers/{uid}/cancel](https://developers.newline53.com/reference/put_transfers-uid-cancel)

A Transfer is the Action of moving assets between two Synthetic Accounts. Most asset movement initiated by your application will result in a Transfer. Asset movement is determined by the makeup of assets in both participating accounts, the Synthetic Account Type of the participating accounts, the available Custodial Accounts for all participating Customers, and the overall Program configuration. A Transfer can never be initiated between two external accounts.

### Company ID Behavior

The company_id field is optional when initiating a transfer. However, it becomes required if your organization has multiple company IDs or multiple custodial accounts, to ensure accurate routing of funds. If provided, the value will be validated.

In sandbox environments:

- `company_id` is optional and will be validated if supplied.
- `company_id` and custodial accounts are always in a 1:1 relationship.

> **Note** 
> Any time a Transfer is created, it creates 1+ Transactions, which are returned in the response payload for a successful Transfer. Please note or record these uids, as they will be needed to query the Transaction object and any associated key data points. For instance, Fed IMAD or CHIPS SSN is used for outgoing wires.

### Available Operations

* [list](#list) - List Transfers
* [create](#create) - Initiate a Transfer
* [get](#get) - Get a single Transfer
* [cancel](#cancel) - Cancel a Transfer

## list

Retrieves a list of Transfers filtered by the given parameters. Transfers facilitate the movement of assets between accounts, enabling transactions such as payments and withdrawals.


### Example Usage

<!-- UsageSnippet language="java" operationID="listTransfers" method="get" path="/transfers" example="transactions" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.ListTransfersRequest;
import com.newline53.sdk.models.operations.ListTransfersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        ListTransfersRequest req = ListTransfersRequest.builder()
                .customerUid("uKxmLxUEiSj5h4M3")
                .externalUid("client-generated-id")
                .poolUid("wTSMX1GubP21ev2h")
                .syntheticAccountUid("4XkJnsfHsuqrxmeX")
                .build();

        ListTransfersResponse res = sdk.transfers().list()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `request`                                                               | [ListTransfersRequest](../../models/operations/ListTransfersRequest.md) | :heavy_check_mark:                                                      | The request object to use for the request.                              |

### Response

**[ListTransfersResponse](../../models/operations/ListTransfersResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## create

Attempt to initiate a Transfer between two Synthetic Accounts. Before the Transfer will be initiated, several checks will be performed to ensure there is sufficient balance in the source account and that the initiating Customer has all the necessary access to both Synthetic Accounts.

The Synthetic Accounts allowed in a Transfer request are listed below

- between a liability Synthetic Account in the `general` category and a Synthetic Account in the `ach_external`, `wire_external`, or `instant_payment_external` category
- between two liability Synthetic Accounts in the `general` category that are also owned by the same Customer.

> **Note**
> Please note that if utilizing the Transmitter information, the initiator type should be set to transmitter. If not set, the field defaults to customer and any data provided in that field will be ignored.

### Example Usage: ach

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="ach" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferAchRequest;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferPaymentTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import com.newline53.sdk.models.operations.CreateTransferSecCodeRequest;
import com.newline53.sdk.models.operations.CreateTransferServiceProcessingRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .ach(CreateTransferAchRequest.builder()
                    .originatorName("Mr. Hyyt Meiser")
                    .secCode(CreateTransferSecCodeRequest.CCD)
                    .entryDescription("ACH Entry")
                    .serviceProcessing(CreateTransferServiceProcessingRequest.STANDARD)
                    .effectiveEntryDate("2023-12-21")
                    .companyId("HJK867")
                    .companyDiscretionaryData("Some data")
                    .prenote(false)
                    .paymentType(CreateTransferPaymentTypeRequest.S)
                    .idNumber("4270465600")
                    .addenda("Having said that, this")
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: ach_transfer

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="ach_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferAchRequest;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.CreateTransferIntermediaryBankAddressRequest;
import com.newline53.sdk.models.operations.CreateTransferPaymentTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import com.newline53.sdk.models.operations.CreateTransferSecCodeRequest;
import com.newline53.sdk.models.operations.CreateTransferServiceProcessingRequest;
import com.newline53.sdk.models.operations.CreateTransferWireRequest;
import com.newline53.sdk.models.operations.CreateTransferWireTransmitterRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .ach(CreateTransferAchRequest.builder()
                    .originatorName("J. Fred Muggs")
                    .secCode(CreateTransferSecCodeRequest.CIE)
                    .entryDescription("ACH Entry")
                    .serviceProcessing(CreateTransferServiceProcessingRequest.SAMEDAY)
                    .effectiveEntryDate("2023-12-01")
                    .companyId("ABC-123456")
                    .companyDiscretionaryData("ABC.123")
                    .prenote(false)
                    .paymentType(CreateTransferPaymentTypeRequest.ST)
                    .idNumber("4270465600")
                    .build())
                .instantPayment(CreateTransferInstantPaymentRequest.builder()
                    .instantPaymentTransmitter(CreateTransferInstantPaymentTransmitter.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .build())
                    .memo("For the 6-5-23 shipment of pineapple popsicles")
                    .build())
                .wire(CreateTransferWireRequest.builder()
                    .intermediaryBankAddress(CreateTransferIntermediaryBankAddressRequest.builder()
                        .line1("345 Def Ave")
                        .line2("San Francisco")
                        .line3("CA 94016")
                        .country("US")
                        .build())
                    .intermediaryBankName("Fidelity Fiduciary Bank")
                    .intermediaryBankRoutingNumber("923456789")
                    .wireInstructions("Send ASAP")
                    .wireTransmitter(CreateTransferWireTransmitterRequest.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .line1("123 Abc St.")
                        .country("US")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .build())
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="instant_payment" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .instantPayment(CreateTransferInstantPaymentRequest.builder()
                    .instantPaymentTransmitter(CreateTransferInstantPaymentTransmitter.builder()
                        .name("Royalty Asset Management")
                        .transmitterIdentifier("123456789")
                        .streetNumber("123")
                        .street1("Abc St.")
                        .city("Boring")
                        .state("OR")
                        .postalCode("97009")
                        .country(null)
                        .build())
                    .memo("To unfreeze the prince's assets")
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_transfer

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="instant_payment_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferAchRequest;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.CreateTransferIntermediaryBankAddressRequest;
import com.newline53.sdk.models.operations.CreateTransferPaymentTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import com.newline53.sdk.models.operations.CreateTransferSecCodeRequest;
import com.newline53.sdk.models.operations.CreateTransferServiceProcessingRequest;
import com.newline53.sdk.models.operations.CreateTransferWireRequest;
import com.newline53.sdk.models.operations.CreateTransferWireTransmitterRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .ach(CreateTransferAchRequest.builder()
                    .originatorName("J. Fred Muggs")
                    .secCode(CreateTransferSecCodeRequest.CIE)
                    .entryDescription("ACH Entry")
                    .serviceProcessing(CreateTransferServiceProcessingRequest.SAMEDAY)
                    .effectiveEntryDate("2023-12-01")
                    .companyId("ABC-123456")
                    .companyDiscretionaryData("ABC.123")
                    .prenote(false)
                    .paymentType(CreateTransferPaymentTypeRequest.ST)
                    .idNumber("4270465600")
                    .build())
                .instantPayment(CreateTransferInstantPaymentRequest.builder()
                    .instantPaymentTransmitter(CreateTransferInstantPaymentTransmitter.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .build())
                    .memo("For the 6-5-23 shipment of pineapple popsicles")
                    .build())
                .wire(CreateTransferWireRequest.builder()
                    .intermediaryBankAddress(CreateTransferIntermediaryBankAddressRequest.builder()
                        .line1("345 Def Ave")
                        .line2("San Francisco")
                        .line3("CA 94016")
                        .country("US")
                        .build())
                    .intermediaryBankName("Fidelity Fiduciary Bank")
                    .intermediaryBankRoutingNumber("923456789")
                    .wireInstructions("Send ASAP")
                    .wireTransmitter(CreateTransferWireTransmitterRequest.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .line1("123 Abc St.")
                        .country("US")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .build())
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="wire" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferIntermediaryBankAddressRequest;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import com.newline53.sdk.models.operations.CreateTransferWireRequest;
import com.newline53.sdk.models.operations.CreateTransferWireTransmitterRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .wire(CreateTransferWireRequest.builder()
                    .intermediaryBankAddress(CreateTransferIntermediaryBankAddressRequest.builder()
                        .line1("345 Def Ave")
                        .line2("San Francisco")
                        .line3("CA 94016")
                        .country("US")
                        .build())
                    .intermediaryBankName("Fidelity Fiduciary Bank")
                    .intermediaryBankRoutingNumber("923456789")
                    .wireInstructions("Please send ASAP")
                    .wireTransmitter(CreateTransferWireTransmitterRequest.builder()
                        .name("Top Tier Tacos")
                        .transmitterIdentifier("123456789")
                        .line1("123 Abc St.")
                        .country("US")
                        .build())
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_transfer

<!-- UsageSnippet language="java" operationID="createTransfer" method="post" path="/transfers" example="wire_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.CreateTransferAchRequest;
import com.newline53.sdk.models.operations.CreateTransferInitiatorTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentRequest;
import com.newline53.sdk.models.operations.CreateTransferInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.CreateTransferIntermediaryBankAddressRequest;
import com.newline53.sdk.models.operations.CreateTransferPaymentTypeRequest;
import com.newline53.sdk.models.operations.CreateTransferRequest;
import com.newline53.sdk.models.operations.CreateTransferResponse;
import com.newline53.sdk.models.operations.CreateTransferSecCodeRequest;
import com.newline53.sdk.models.operations.CreateTransferServiceProcessingRequest;
import com.newline53.sdk.models.operations.CreateTransferWireRequest;
import com.newline53.sdk.models.operations.CreateTransferWireTransmitterRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        CreateTransferRequest req = CreateTransferRequest.builder()
                .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                .usdTransferAmount("12.34")
                .externalUid("partner-generated-id")
                .destinationCustomerUid("iDtmSA52zRhgN4iy")
                .initiatorType(CreateTransferInitiatorTypeRequest.CUSTOMER)
                .ach(CreateTransferAchRequest.builder()
                    .originatorName("J. Fred Muggs")
                    .secCode(CreateTransferSecCodeRequest.CIE)
                    .entryDescription("ACH Entry")
                    .serviceProcessing(CreateTransferServiceProcessingRequest.SAMEDAY)
                    .effectiveEntryDate("2023-12-01")
                    .companyId("ABC-123456")
                    .companyDiscretionaryData("ABC.123")
                    .prenote(false)
                    .paymentType(CreateTransferPaymentTypeRequest.ST)
                    .idNumber("4270465600")
                    .build())
                .instantPayment(CreateTransferInstantPaymentRequest.builder()
                    .instantPaymentTransmitter(CreateTransferInstantPaymentTransmitter.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .streetNumber("123abc")
                        .street1("Abc St.")
                        .city("Chicago")
                        .state("IL")
                        .postalCode("60301")
                        .country(null)
                        .build())
                    .memo("For the 6-5-23 shipment of pineapple popsicles")
                    .build())
                .wire(CreateTransferWireRequest.builder()
                    .intermediaryBankAddress(CreateTransferIntermediaryBankAddressRequest.builder()
                        .line1("345 Def Ave")
                        .line2("San Francisco")
                        .line3("CA 94016")
                        .country("US")
                        .build())
                    .intermediaryBankName("Fidelity Fiduciary Bank")
                    .intermediaryBankRoutingNumber("923456789")
                    .wireInstructions("Send ASAP")
                    .wireTransmitter(CreateTransferWireTransmitterRequest.builder()
                        .name("Marge's Roofing Inc")
                        .transmitterIdentifier("123456789012ABC")
                        .line1("123 Abc St.")
                        .country("US")
                        .line2("Boring, Oregon 97009")
                        .line3(null)
                        .build())
                    .build())
                .build();

        CreateTransferResponse res = sdk.transfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [CreateTransferRequest](../../models/operations/CreateTransferRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[CreateTransferResponse](../../models/operations/CreateTransferResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## get

Retrieves a single Transfer resource along with its details, including status, participating accounts, and associated Transactions.

Filter parameters are not case sensitive but will only return exact matches.

### Example Usage: ach_transfer

<!-- UsageSnippet language="java" operationID="getTransfer" method="get" path="/transfers/{uid}" example="ach_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransferResponse res = sdk.transfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: canceled_transfer

<!-- UsageSnippet language="java" operationID="getTransfer" method="get" path="/transfers/{uid}" example="canceled_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransferResponse res = sdk.transfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_transfer

<!-- UsageSnippet language="java" operationID="getTransfer" method="get" path="/transfers/{uid}" example="instant_payment_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransferResponse res = sdk.transfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_transfer

<!-- UsageSnippet language="java" operationID="getTransfer" method="get" path="/transfers/{uid}" example="wire_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GetTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetTransferResponse res = sdk.transfers().get()
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

**[GetTransferResponse](../../models/operations/GetTransferResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## cancel

Transfers must be canceled by the originating Customer (or Authorized Representative).

Transfers can only enter the canceled state if Newline receives a request while the Transfer is in `queued` or `pending states`.

A cancellation request during the `pending` state is not guaranteed, as this state may include payment execution. At that stage, a cancel request will result in an error.

### Example Usage: canceled_transfer

<!-- UsageSnippet language="java" operationID="put_/transfers/{uid}/cancel" method="put" path="/transfers/{uid}/cancel" example="canceled_transfer" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransfersUidCancelBadRequestException;
import com.newline53.sdk.models.errors.PutTransfersUidCancelUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutTransfersUidCancelRequestBody;
import com.newline53.sdk.models.operations.PutTransfersUidCancelResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransfersUidCancelBadRequestException, PutTransfersUidCancelUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransfersUidCancelResponse res = sdk.transfers().cancel()
                .uid("<id>")
                .body(PutTransfersUidCancelRequestBody.builder()
                    .authorizedRepresentativeName("Swee'Pea")
                    .cancellationReason("Transfer submitted by accident")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: invalid_transfer_cancellation

<!-- UsageSnippet language="java" operationID="put_/transfers/{uid}/cancel" method="put" path="/transfers/{uid}/cancel" example="invalid_transfer_cancellation" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransfersUidCancelBadRequestException;
import com.newline53.sdk.models.errors.PutTransfersUidCancelUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutTransfersUidCancelRequestBody;
import com.newline53.sdk.models.operations.PutTransfersUidCancelResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransfersUidCancelBadRequestException, PutTransfersUidCancelUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransfersUidCancelResponse res = sdk.transfers().cancel()
                .uid("<id>")
                .body(PutTransfersUidCancelRequestBody.builder()
                    .authorizedRepresentativeName("Swee'Pea")
                    .cancellationReason("Transfer submitted by accident")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: transfer_cancellation_error

<!-- UsageSnippet language="java" operationID="put_/transfers/{uid}/cancel" method="put" path="/transfers/{uid}/cancel" example="transfer_cancellation_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PutTransfersUidCancelBadRequestException;
import com.newline53.sdk.models.errors.PutTransfersUidCancelUnprocessableEntityException;
import com.newline53.sdk.models.operations.PutTransfersUidCancelRequestBody;
import com.newline53.sdk.models.operations.PutTransfersUidCancelResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PutTransfersUidCancelBadRequestException, PutTransfersUidCancelUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PutTransfersUidCancelResponse res = sdk.transfers().cancel()
                .uid("<id>")
                .body(PutTransfersUidCancelRequestBody.builder()
                    .authorizedRepresentativeName("Swee'Pea")
                    .cancellationReason("Transfer submitted by accident")
                    .build())
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                       | Type                                                                                            | Required                                                                                        | Description                                                                                     |
| ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| `uid`                                                                                           | *String*                                                                                        | :heavy_check_mark:                                                                              | Newline-generated unique id resource specific to the current endpoint                           |
| `body`                                                                                          | [PutTransfersUidCancelRequestBody](../../models/operations/PutTransfersUidCancelRequestBody.md) | :heavy_check_mark:                                                                              | N/A                                                                                             |

### Response

**[PutTransfersUidCancelResponse](../../models/operations/PutTransfersUidCancelResponse.md)**

### Errors

| Error Type                                                      | Status Code                                                     | Content Type                                                    |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| models/errors/PutTransfersUidCancelBadRequestException          | 400                                                             | application/json                                                |
| models/errors/PutTransfersUidCancelUnprocessableEntityException | 422                                                             | application/json                                                |
| models/errors/APIException                                      | 4XX, 5XX                                                        | \*/\*                                                           |