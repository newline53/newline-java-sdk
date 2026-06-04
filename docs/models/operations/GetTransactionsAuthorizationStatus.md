# GetTransactionsAuthorizationStatus

A value indicating the current state of authorization for this transaction.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsAuthorizationStatus;

GetTransactionsAuthorizationStatus value = GetTransactionsAuthorizationStatus.AUTO_APPROVED;

// Open enum: use .of() to create instances from custom string values
GetTransactionsAuthorizationStatus custom = GetTransactionsAuthorizationStatus.of("custom_value");
```


## Values

| Name                              | Value                             |
| --------------------------------- | --------------------------------- |
| `AUTO_APPROVED`                   | auto_approved                     |
| `AUTO_DENIED`                     | auto_denied                       |
| `CLIENT_APPROVAL_PENDING`         | client_approval_pending           |
| `CLIENT_APPROVED`                 | client_approved                   |
| `CLIENT_DENIED`                   | client_denied                     |
| `CLIENT_UNANSWERED_AUTO_APPROVED` | client_unanswered_auto_approved   |
| `CLIENT_UNANSWERED_AUTO_DENIED`   | client_unanswered_auto_denied     |
| `CREATED`                         | created                           |