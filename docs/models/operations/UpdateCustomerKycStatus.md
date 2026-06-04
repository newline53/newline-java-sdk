# UpdateCustomerKycStatus

A value indicating the state of KYC/AML evaluation.


## Example Usage

```java
import com.newline53.sdk.models.operations.UpdateCustomerKycStatus;

UpdateCustomerKycStatus value = UpdateCustomerKycStatus.MANUAL_REVIEW;

// Open enum: use .of() to create instances from custom string values
UpdateCustomerKycStatus custom = UpdateCustomerKycStatus.of("custom_value");
```


## Values

| Name                         | Value                        |
| ---------------------------- | ---------------------------- |
| `MANUAL_REVIEW`              | manual_review                |
| `APPROVED`                   | approved                     |
| `DENIED`                     | denied                       |
| `PENDING_ID_DOCUMENTS`       | pending_id_documents         |
| `PENDING_POA_DOCUMENTS`      | pending_poa_documents        |
| `PENDING_IDANDPOA_DOCUMENTS` | pending_idandpoa_documents   |
| `DOCUMENTS_PROVIDED`         | documents_provided           |
| `DOCUMENTS_REJECTED`         | documents_rejected           |
| `RETAKE_IMAGES`              | retake_images                |
| `PRE_VERIFIED`               | pre_verified                 |