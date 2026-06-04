# CombinedTransfers

## Overview

### Available Operations

* [list](#list) - List Combined Transfers
* [create](#create) - Create a new Combined Transfer
* [get](#get) - Get a single Combined Transfer

## list

Retrieves a list of Combined Transfers. These represent transactions where both a counterparty Synthetic Account and a Transfer were created in a single API call. You can filter results by status and other parameters.


### Example Usage

<!-- UsageSnippet language="java" operationID="get_/combined_transfers" method="get" path="/combined_transfers" example="combined_transfers_list" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.GetCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.GetCombinedTransfersQueryParamStatus;
import com.newline53.sdk.models.operations.GetCombinedTransfersRequest;
import com.newline53.sdk.models.operations.GetCombinedTransfersResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws GetCombinedTransfersForbiddenException, GetCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCombinedTransfersRequest req = GetCombinedTransfersRequest.builder()
                .createdAtAfter(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .createdAtBefore(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                .externalUid("client-generated-id")
                .status(GetCombinedTransfersQueryParamStatus.COMPLETED)
                .syntheticAccountExternalUid("client-generated-id")
                .syntheticAccountPoolUid("wTSMX1GubP21ev2h")
                .syntheticAccountUid("7UvkHn3Ss9AbWe2c")
                .transferCustomerUid("wTSMX1GubP21ev2h")
                .transferExternalUid("client-generated-id")
                .transferUid("7UvkHn3Ss9AbWe2c")
                .build();

        GetCombinedTransfersResponse res = sdk.combinedTransfers().list()
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
| `request`                                                                             | [GetCombinedTransfersRequest](../../models/operations/GetCombinedTransfersRequest.md) | :heavy_check_mark:                                                                    | The request object to use for the request.                                            |

### Response

**[GetCombinedTransfersResponse](../../models/operations/GetCombinedTransfersResponse.md)**

### Errors

| Error Type                                                     | Status Code                                                    | Content Type                                                   |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| models/errors/GetCombinedTransfersForbiddenException           | 403                                                            | application/json                                               |
| models/errors/GetCombinedTransfersUnprocessableEntityException | 422                                                            | application/json                                               |
| models/errors/APIException                                     | 4XX, 5XX                                                       | \*/\*                                                          |

## create

Creates a Combined Transfer by simultaneously creating a counterparty Synthetic Account and initiating a Transfer. This streamlines asset movement by reducing the number of steps required to set up and execute a transaction.


### Example Usage: ach_transfer_to_existing_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="ach_transfer_to_existing_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .build())
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: ach_transfer_to_new_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="ach_transfer_to_new_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersAccountType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.SyntheticAccountAch;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("abcd12345")
                    .name("Spinach Fund")
                    .poolUid("axz9sbUgRVu5wqbw")
                    .syntheticAccountTypeUid("UyjH72G9KAhmbpNS")
                    .routingNumber("04200031")
                    .accountNumber("1234567890")
                    .ach(SyntheticAccountAch.builder()
                        .accountType(PostCombinedTransfersAccountType.CHECKING)
                        .counterpartyName("awesome counterparty")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .build())
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: combined_transfer_creation_error

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="combined_transfer_creation_error" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersAccountType;
import com.newline53.sdk.models.operations.PostCombinedTransfersCounterpartyBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersPaymentType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountAch;
import com.newline53.sdk.models.operations.SyntheticAccountInstantPayment;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.SyntheticAccountWire;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                    .ach(SyntheticAccountAch.builder()
                        .accountType(PostCombinedTransfersAccountType.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(SyntheticAccountInstantPayment.builder()
                        .counterpartyAddress(PostCombinedTransfersInstantPaymentCounterpartyAddress.builder()
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
                    .wire(SyntheticAccountWire.builder()
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyAddress(PostCombinedTransfersWireCounterpartyAddress.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyBankAddress(PostCombinedTransfersCounterpartyBankAddress.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                    .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                    .usdTransferAmount("12.34")
                    .externalUid("partner-generated-id")
                    .destinationCustomerUid("iDtmSA52zRhgN4iy")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .companyId("ABC-123456")
                        .companyDiscretionaryData("ABC.123")
                        .prenote(false)
                        .paymentType(PostCombinedTransfersPaymentType.ST)
                        .idNumber("4270465600")
                        .build())
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("345 Def Ave")
                            .line2("San Francisco")
                            .line3("CA 94016")
                            .country("US")
                            .build())
                        .intermediaryBankName("Fidelity Fiduciary Bank")
                        .intermediaryBankRoutingNumber("923456789")
                        .wireInstructions("Send ASAP")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Marge's Roofing Inc")
                            .transmitterIdentifier("123456789012ABC")
                            .line1("123 Abc St.")
                            .country("US")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .build())
                        .build())
                    .build())
                .externalUid("YrfDrfVRgpPgnhF5")
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: combined_transfer_external_uid_taken

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="combined_transfer_external_uid_taken" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersAccountType;
import com.newline53.sdk.models.operations.PostCombinedTransfersCounterpartyBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersPaymentType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountAch;
import com.newline53.sdk.models.operations.SyntheticAccountInstantPayment;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.SyntheticAccountWire;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                    .ach(SyntheticAccountAch.builder()
                        .accountType(PostCombinedTransfersAccountType.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(SyntheticAccountInstantPayment.builder()
                        .counterpartyAddress(PostCombinedTransfersInstantPaymentCounterpartyAddress.builder()
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
                    .wire(SyntheticAccountWire.builder()
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyAddress(PostCombinedTransfersWireCounterpartyAddress.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyBankAddress(PostCombinedTransfersCounterpartyBankAddress.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                    .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                    .usdTransferAmount("12.34")
                    .externalUid("partner-generated-id")
                    .destinationCustomerUid("iDtmSA52zRhgN4iy")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .companyId("ABC-123456")
                        .companyDiscretionaryData("ABC.123")
                        .prenote(false)
                        .paymentType(PostCombinedTransfersPaymentType.ST)
                        .idNumber("4270465600")
                        .build())
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("345 Def Ave")
                            .line2("San Francisco")
                            .line3("CA 94016")
                            .country("US")
                            .build())
                        .intermediaryBankName("Fidelity Fiduciary Bank")
                        .intermediaryBankRoutingNumber("923456789")
                        .wireInstructions("Send ASAP")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Marge's Roofing Inc")
                            .transmitterIdentifier("123456789012ABC")
                            .line1("123 Abc St.")
                            .country("US")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .build())
                        .build())
                    .build())
                .externalUid("YrfDrfVRgpPgnhF5")
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: combined_transfers_disabled

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="combined_transfers_disabled" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersAccountType;
import com.newline53.sdk.models.operations.PostCombinedTransfersCounterpartyBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersPaymentType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountAch;
import com.newline53.sdk.models.operations.SyntheticAccountInstantPayment;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.SyntheticAccountWire;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                    .ach(SyntheticAccountAch.builder()
                        .accountType(PostCombinedTransfersAccountType.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(SyntheticAccountInstantPayment.builder()
                        .counterpartyAddress(PostCombinedTransfersInstantPaymentCounterpartyAddress.builder()
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
                    .wire(SyntheticAccountWire.builder()
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyAddress(PostCombinedTransfersWireCounterpartyAddress.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyBankAddress(PostCombinedTransfersCounterpartyBankAddress.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                    .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                    .usdTransferAmount("12.34")
                    .externalUid("partner-generated-id")
                    .destinationCustomerUid("iDtmSA52zRhgN4iy")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .companyId("ABC-123456")
                        .companyDiscretionaryData("ABC.123")
                        .prenote(false)
                        .paymentType(PostCombinedTransfersPaymentType.ST)
                        .idNumber("4270465600")
                        .build())
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("345 Def Ave")
                            .line2("San Francisco")
                            .line3("CA 94016")
                            .country("US")
                            .build())
                        .intermediaryBankName("Fidelity Fiduciary Bank")
                        .intermediaryBankRoutingNumber("923456789")
                        .wireInstructions("Send ASAP")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Marge's Roofing Inc")
                            .transmitterIdentifier("123456789012ABC")
                            .line1("123 Abc St.")
                            .country("US")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .build())
                        .build())
                    .build())
                .externalUid("YrfDrfVRgpPgnhF5")
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_transfer_to_existing_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="instant_payment_transfer_to_existing_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: instant_payment_transfer_to_new_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="instant_payment_transfer_to_new_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.SyntheticAccountInstantPayment;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("abcd12345")
                    .name("Spinach Fund")
                    .poolUid("axz9sbUgRVu5wqbw")
                    .syntheticAccountTypeUid("UyjH72G9KAhmbpNS")
                    .routingNumber("04200031")
                    .accountNumber("1234567890")
                    .instantPayment(SyntheticAccountInstantPayment.builder()
                        .counterpartyAddress(PostCombinedTransfersInstantPaymentCounterpartyAddress.builder()
                            .streetNumber("123")
                            .street1("Main street")
                            .city("Brooklyn")
                            .state("IA")
                            .postalCode("12345-6789")
                            .country("US")
                            .build())
                        .counterpartyName("awesome counterparty")
                        .email("address@domain.com")
                        .phone("2121112233")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: queued

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="queued" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersAccountType;
import com.newline53.sdk.models.operations.PostCombinedTransfersCounterpartyBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersInstantPaymentTransmitter;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersPaymentType;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersSecCode;
import com.newline53.sdk.models.operations.PostCombinedTransfersServiceProcessing;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireCounterpartyAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountAch;
import com.newline53.sdk.models.operations.SyntheticAccountInstantPayment;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.SyntheticAccountWire;
import com.newline53.sdk.models.operations.TransferAch;
import com.newline53.sdk.models.operations.TransferInstantPayment;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .routingNumber("123456789")
                    .accountNumber("123456789012")
                    .externalProcessorToken("processor-sandbox-96d86f35-ef58-4e4a-826f-4870b5d677f2")
                    .ach(SyntheticAccountAch.builder()
                        .accountType(PostCombinedTransfersAccountType.CHECKING)
                        .counterpartyName("Thelma's Flooring LLC")
                        .build())
                    .instantPayment(SyntheticAccountInstantPayment.builder()
                        .counterpartyAddress(PostCombinedTransfersInstantPaymentCounterpartyAddress.builder()
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
                    .wire(SyntheticAccountWire.builder()
                        .counterpartyName("Marge's Roofing Inc")
                        .counterpartyAddress(PostCombinedTransfersWireCounterpartyAddress.builder()
                            .line1("234 Xyz Rd")
                            .line2("APT 5")
                            .line3("Boston, MA 02110")
                            .country("US")
                            .build())
                        .counterpartyBankAddress(PostCombinedTransfersCounterpartyBankAddress.builder()
                            .line1("123 Abc St.")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .country(null)
                            .build())
                        .counterpartyBankName("East West Regional Bank")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("exMDShw6yM3NHLYV")
                    .initiatingCustomerUid("iDtmSA52zRhgN4iy")
                    .usdTransferAmount("12.34")
                    .externalUid("partner-generated-id")
                    .destinationCustomerUid("iDtmSA52zRhgN4iy")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .ach(TransferAch.builder()
                        .originatorName("J. Fred Muggs")
                        .secCode(PostCombinedTransfersSecCode.CIE)
                        .entryDescription("ACH Entry")
                        .serviceProcessing(PostCombinedTransfersServiceProcessing.SAMEDAY)
                        .effectiveEntryDate("2023-12-01")
                        .companyId("ABC-123456")
                        .companyDiscretionaryData("ABC.123")
                        .prenote(false)
                        .paymentType(PostCombinedTransfersPaymentType.ST)
                        .idNumber("4270465600")
                        .build())
                    .instantPayment(TransferInstantPayment.builder()
                        .instantPaymentTransmitter(PostCombinedTransfersInstantPaymentTransmitter.builder()
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
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("345 Def Ave")
                            .line2("San Francisco")
                            .line3("CA 94016")
                            .country("US")
                            .build())
                        .intermediaryBankName("Fidelity Fiduciary Bank")
                        .intermediaryBankRoutingNumber("923456789")
                        .wireInstructions("Send ASAP")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Marge's Roofing Inc")
                            .transmitterIdentifier("123456789012ABC")
                            .line1("123 Abc St.")
                            .country("US")
                            .line2("Boring, Oregon 97009")
                            .line3(null)
                            .build())
                        .build())
                    .build())
                .externalUid("YrfDrfVRgpPgnhF5")
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_transfer_to_existing_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="wire_transfer_to_existing_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("partner-generated-id")
                    .name("New Resource Name")
                    .poolUid("kaxHFJnWvJxRJZxq")
                    .syntheticAccountTypeUid("fRMwt6H14ovFUz1s")
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("123 Main St")
                            .line2("Brooklyn NY")
                            .country("US")
                            .build())
                        .intermediaryBankName("Big Bank Inc")
                        .intermediaryBankRoutingNumber("123456789")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Bunker LLC")
                            .transmitterIdentifier("1234567890")
                            .line1("456 Second St")
                            .country("US")
                            .line2("Queens NY")
                            .build())
                        .build())
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
                .request(req)
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: wire_transfer_to_new_account

<!-- UsageSnippet language="java" operationID="post_/combined_transfers" method="post" path="/combined_transfers" example="wire_transfer_to_new_account" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.PostCombinedTransfersForbiddenException;
import com.newline53.sdk.models.errors.PostCombinedTransfersUnprocessableEntityException;
import com.newline53.sdk.models.operations.PostCombinedTransfersInitiatorType;
import com.newline53.sdk.models.operations.PostCombinedTransfersIntermediaryBankAddress;
import com.newline53.sdk.models.operations.PostCombinedTransfersRequest;
import com.newline53.sdk.models.operations.PostCombinedTransfersResponse;
import com.newline53.sdk.models.operations.PostCombinedTransfersWireTransmitter;
import com.newline53.sdk.models.operations.SyntheticAccountRequest;
import com.newline53.sdk.models.operations.SyntheticAccountWire;
import com.newline53.sdk.models.operations.TransferRequest;
import com.newline53.sdk.models.operations.TransferWire;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws PostCombinedTransfersForbiddenException, PostCombinedTransfersUnprocessableEntityException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        PostCombinedTransfersRequest req = PostCombinedTransfersRequest.builder()
                .syntheticAccount(SyntheticAccountRequest.builder()
                    .externalUid("abcd12345")
                    .name("Spinach Fund")
                    .poolUid("axz9sbUgRVu5wqbw")
                    .syntheticAccountTypeUid("UyjH72G9KAhmbpNS")
                    .routingNumber("04200031")
                    .accountNumber("1234567890")
                    .wire(SyntheticAccountWire.builder()
                        .counterpartyName("Awesome Counterparty")
                        .counterpartyBankName("Biggest Bank Inc")
                        .build())
                    .build())
                .transfer(TransferRequest.builder()
                    .sourceSyntheticAccountUid("4XkJnsfHsuqrxmeX")
                    .destinationSyntheticAccountUid("beHyJNeBU65Xv8LK")
                    .initiatingCustomerUid("axz9sbUgRVu5wqbw")
                    .usdTransferAmount("12.34")
                    .initiatorType(PostCombinedTransfersInitiatorType.CUSTOMER)
                    .wire(TransferWire.builder()
                        .intermediaryBankAddress(PostCombinedTransfersIntermediaryBankAddress.builder()
                            .line1("123 Main St")
                            .line2("Brooklyn NY")
                            .country("US")
                            .build())
                        .intermediaryBankName("Big Bank Inc")
                        .intermediaryBankRoutingNumber("123456789")
                        .wireTransmitter(PostCombinedTransfersWireTransmitter.builder()
                            .name("Bunker LLC")
                            .transmitterIdentifier("1234567890")
                            .line1("456 Second St")
                            .country("US")
                            .line2("Queens NY")
                            .build())
                        .build())
                    .build())
                .build();

        PostCombinedTransfersResponse res = sdk.combinedTransfers().create()
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
| `request`                                                                               | [PostCombinedTransfersRequest](../../models/operations/PostCombinedTransfersRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[PostCombinedTransfersResponse](../../models/operations/PostCombinedTransfersResponse.md)**

### Errors

| Error Type                                                      | Status Code                                                     | Content Type                                                    |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| models/errors/PostCombinedTransfersForbiddenException           | 403                                                             | application/json                                                |
| models/errors/PostCombinedTransfersUnprocessableEntityException | 422                                                             | application/json                                                |
| models/errors/APIException                                      | 4XX, 5XX                                                        | \*/\*                                                           |

## get

Retrieves details about a specific Combined Transfer, including the status, participating accounts, and associated metadata. Statuses include `queued`, `pending`, `failed`, and `completed`.

### Example Usage: completed

<!-- UsageSnippet language="java" operationID="get_/combined_transfers/{uid}" method="get" path="/combined_transfers/{uid}" example="completed" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException;
import com.newline53.sdk.models.operations.GetCombinedTransfersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetCombinedTransfersUidForbiddenException, GetCombinedTransfersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCombinedTransfersUidResponse res = sdk.combinedTransfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: failed

<!-- UsageSnippet language="java" operationID="get_/combined_transfers/{uid}" method="get" path="/combined_transfers/{uid}" example="failed" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException;
import com.newline53.sdk.models.operations.GetCombinedTransfersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetCombinedTransfersUidForbiddenException, GetCombinedTransfersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCombinedTransfersUidResponse res = sdk.combinedTransfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: pending

<!-- UsageSnippet language="java" operationID="get_/combined_transfers/{uid}" method="get" path="/combined_transfers/{uid}" example="pending" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException;
import com.newline53.sdk.models.operations.GetCombinedTransfersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetCombinedTransfersUidForbiddenException, GetCombinedTransfersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCombinedTransfersUidResponse res = sdk.combinedTransfers().get()
                .uid("<id>")
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
### Example Usage: queued

<!-- UsageSnippet language="java" operationID="get_/combined_transfers/{uid}" method="get" path="/combined_transfers/{uid}" example="queued" -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidForbiddenException;
import com.newline53.sdk.models.errors.GetCombinedTransfersUidNotFoundException;
import com.newline53.sdk.models.operations.GetCombinedTransfersUidResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws GetCombinedTransfersUidForbiddenException, GetCombinedTransfersUidNotFoundException, Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GetCombinedTransfersUidResponse res = sdk.combinedTransfers().get()
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

**[GetCombinedTransfersUidResponse](../../models/operations/GetCombinedTransfersUidResponse.md)**

### Errors

| Error Type                                              | Status Code                                             | Content Type                                            |
| ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- |
| models/errors/GetCombinedTransfersUidForbiddenException | 403                                                     | application/json                                        |
| models/errors/GetCombinedTransfersUidNotFoundException  | 404                                                     | application/json                                        |
| models/errors/APIException                              | 4XX, 5XX                                                | \*/\*                                                   |