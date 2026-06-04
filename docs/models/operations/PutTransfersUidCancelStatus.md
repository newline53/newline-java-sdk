# PutTransfersUidCancelStatus

A value indicating the overall status of the Transfer.


## Example Usage

```java
import com.newline53.sdk.models.operations.PutTransfersUidCancelStatus;

PutTransfersUidCancelStatus value = PutTransfersUidCancelStatus.CANCELED;

// Open enum: use .of() to create instances from custom string values
PutTransfersUidCancelStatus custom = PutTransfersUidCancelStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |