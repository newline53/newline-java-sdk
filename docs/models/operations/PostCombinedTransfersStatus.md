# PostCombinedTransfersStatus

The combined transfer's status

## Example Usage

```java
import com.newline53.sdk.models.operations.PostCombinedTransfersStatus;

PostCombinedTransfersStatus value = PostCombinedTransfersStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
PostCombinedTransfersStatus custom = PostCombinedTransfersStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `PENDING`   | pending     |
| `FAILED`    | failed      |
| `COMPLETED` | completed   |