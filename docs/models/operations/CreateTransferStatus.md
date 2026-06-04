# CreateTransferStatus

A value indicating the overall status of the Transfer.


## Example Usage

```java
import com.newline53.sdk.models.operations.CreateTransferStatus;

CreateTransferStatus value = CreateTransferStatus.CANCELED;

// Open enum: use .of() to create instances from custom string values
CreateTransferStatus custom = CreateTransferStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |