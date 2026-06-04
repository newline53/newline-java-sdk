# GetCombinedTransfersUidStatus

The combined transfer's status

## Example Usage

```java
import com.newline53.sdk.models.operations.GetCombinedTransfersUidStatus;

GetCombinedTransfersUidStatus value = GetCombinedTransfersUidStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
GetCombinedTransfersUidStatus custom = GetCombinedTransfersUidStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `PENDING`   | pending     |
| `FAILED`    | failed      |
| `COMPLETED` | completed   |