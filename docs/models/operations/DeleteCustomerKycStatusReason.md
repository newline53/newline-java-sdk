# DeleteCustomerKycStatusReason

## Example Usage

```java
import com.newline53.sdk.models.operations.DeleteCustomerKycStatusReason;

DeleteCustomerKycStatusReason value = DeleteCustomerKycStatusReason.APPROVED;

// Open enum: use .of() to create instances from custom string values
DeleteCustomerKycStatusReason custom = DeleteCustomerKycStatusReason.of("custom_value");
```


## Values

| Name                                                   | Value                                                  |
| ------------------------------------------------------ | ------------------------------------------------------ |
| `APPROVED`                                             | Approved                                               |
| `VALID_DOCUMENTS_PROVIDED`                             | Valid Documents Provided                               |
| `ARCHIVED_DUE_TO_APPLICATION_INACTIVITY`               | Archived due to application inactivity                 |
| `FURTHER_REVIEW_REQUIRED`                              | Further review required                                |
| `MINOR_AGE_LESS_THAN18_YEARS`                          | Minor (age < 18 years)                                 |
| `INVALID_DOCUMENTS_SUBMITTED_MULTIPLE_TIMES`           | Invalid Documents submitted multiple times             |
| `MINIMUM_REQUIREMENTS_TO_OPEN_AN_ACCOUNT_WERE_NOT_MET` | Minimum requirements to open an account were not met   |
| `INVALID_DOCUMENTS`                                    | Invalid documents                                      |
| `INCORRECT_DOCUMENT_PROVIDED_FOR_ID_DOCUMENT`          | Incorrect document provided for ID document            |
| `EXPIRED_ID`                                           | Expired ID                                             |
| `ILLEGIBLE_ID_DOCUMENT`                                | Illegible ID document                                  |
| `INCORRECT_DOCUMENT_PROVIDED_FOR_POA_DOCUMENT`         | Incorrect document provided for POA document           |
| `INVALID_POA_PROVIDED`                                 | Invalid POA provided                                   |
| `ILLEGIBLE_POA_DOCUMENT`                               | Illegible POA document                                 |