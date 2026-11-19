# GetCombinedTransfersSyntheticAccountStatus

A value indicating the overall state of this Synthetic Account.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetCombinedTransfersSyntheticAccountStatus;

GetCombinedTransfersSyntheticAccountStatus value = GetCombinedTransfersSyntheticAccountStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
GetCombinedTransfersSyntheticAccountStatus custom = GetCombinedTransfersSyntheticAccountStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `INITIATED` | initiated   |
| `ACTIVE`    | active      |
| `ARCHIVED`  | archived    |
| `FAILED`    | failed      |