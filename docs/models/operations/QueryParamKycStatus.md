# QueryParamKycStatus

Filter by KYC status. Multiple values are allowed e.g. `kyc_status[]=approved&kyc_status[]=under_review`.

## Example Usage

```java
import com.newline53.sdk.models.operations.QueryParamKycStatus;

QueryParamKycStatus value = QueryParamKycStatus.APPROVED;
```


## Values

| Name                         | Value                        |
| ---------------------------- | ---------------------------- |
| `APPROVED`                   | approved                     |
| `DENIED`                     | denied                       |
| `DOCUMENTS_PROVIDED`         | documents_provided           |
| `DOCUMENTS_REJECTED`         | documents_rejected           |
| `MANUAL_REVIEW`              | manual_review                |
| `PENDING_ID_DOCUMENTS`       | pending_id_documents         |
| `PENDING_POA_DOCUMENTS`      | pending_poa_documents        |
| `PENDING_IDANDPOA_DOCUMENTS` | pending_idandpoa_documents   |
| `RETAKE_IMAGES`              | retake_images                |
| `PRE_VERIFIED`               | pre_verified                 |