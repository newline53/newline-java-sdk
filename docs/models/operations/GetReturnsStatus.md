# GetReturnsStatus

A value indicating the overall status of the Return.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetReturnsStatus;

GetReturnsStatus value = GetReturnsStatus.CANCELED;

// Open enum: use .of() to create instances from custom string values
GetReturnsStatus custom = GetReturnsStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |