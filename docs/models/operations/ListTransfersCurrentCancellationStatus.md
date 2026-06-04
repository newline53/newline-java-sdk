# ListTransfersCurrentCancellationStatus

## Example Usage

```java
import com.newline53.sdk.models.operations.ListTransfersCurrentCancellationStatus;

ListTransfersCurrentCancellationStatus value = ListTransfersCurrentCancellationStatus.PENDING_CANCELLATION;

// Open enum: use .of() to create instances from custom string values
ListTransfersCurrentCancellationStatus custom = ListTransfersCurrentCancellationStatus.of("custom_value");
```


## Values

| Name                      | Value                     |
| ------------------------- | ------------------------- |
| `PENDING_CANCELLATION`    | pending_cancellation      |
| `SUCCESSFUL_CANCELLATION` | successful_cancellation   |
| `FAILED_CANCELLATION`     | failed_cancellation       |