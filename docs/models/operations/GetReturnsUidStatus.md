# GetReturnsUidStatus

A value indicating the overall status of the Return.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetReturnsUidStatus;

GetReturnsUidStatus value = GetReturnsUidStatus.CANCELED;

// Open enum: use .of() to create instances from custom string values
GetReturnsUidStatus custom = GetReturnsUidStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |