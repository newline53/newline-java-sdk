# GetCombinedTransfersDataStatus

The combined transfer's status

## Example Usage

```java
import com.newline53.sdk.models.operations.GetCombinedTransfersDataStatus;

GetCombinedTransfersDataStatus value = GetCombinedTransfersDataStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
GetCombinedTransfersDataStatus custom = GetCombinedTransfersDataStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `PENDING`   | pending     |
| `FAILED`    | failed      |
| `COMPLETED` | completed   |