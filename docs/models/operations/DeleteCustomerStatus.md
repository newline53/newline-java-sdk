# DeleteCustomerStatus

A value indicating the overall state of this Customer.


## Example Usage

```java
import com.newline53.sdk.models.operations.DeleteCustomerStatus;

DeleteCustomerStatus value = DeleteCustomerStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
DeleteCustomerStatus custom = DeleteCustomerStatus.of("custom_value");
```


## Values

| Name                | Value               |
| ------------------- | ------------------- |
| `INITIATED`         | initiated           |
| `QUEUED`            | queued              |
| `IDENTITY_VERIFIED` | identity_verified   |
| `ACTIVE`            | active              |
| `MANUAL_REVIEW`     | manual_review       |
| `REJECTED`          | rejected            |
| `PENDING_ARCHIVAL`  | pending_archival    |
| `ARCHIVED`          | archived            |
| `UNDER_REVIEW`      | under_review        |