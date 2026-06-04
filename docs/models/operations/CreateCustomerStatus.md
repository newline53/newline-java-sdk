# CreateCustomerStatus

A value indicating the overall state of this Customer.


## Example Usage

```java
import com.newline53.sdk.models.operations.CreateCustomerStatus;

CreateCustomerStatus value = CreateCustomerStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
CreateCustomerStatus custom = CreateCustomerStatus.of("custom_value");
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