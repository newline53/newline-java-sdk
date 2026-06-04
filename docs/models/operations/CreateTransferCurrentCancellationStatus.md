# CreateTransferCurrentCancellationStatus

## Example Usage

```java
import com.newline53.sdk.models.operations.CreateTransferCurrentCancellationStatus;

CreateTransferCurrentCancellationStatus value = CreateTransferCurrentCancellationStatus.PENDING_CANCELLATION;

// Open enum: use .of() to create instances from custom string values
CreateTransferCurrentCancellationStatus custom = CreateTransferCurrentCancellationStatus.of("custom_value");
```


## Values

| Name                      | Value                     |
| ------------------------- | ------------------------- |
| `PENDING_CANCELLATION`    | pending_cancellation      |
| `SUCCESSFUL_CANCELLATION` | successful_cancellation   |
| `FAILED_CANCELLATION`     | failed_cancellation       |