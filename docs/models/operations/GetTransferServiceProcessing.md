# GetTransferServiceProcessing

Requested service processing duration for the ACH transfer. Allowable values are: STANDARD or SAMEDAY


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransferServiceProcessing;

GetTransferServiceProcessing value = GetTransferServiceProcessing.SAMEDAY;

// Open enum: use .of() to create instances from custom string values
GetTransferServiceProcessing custom = GetTransferServiceProcessing.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `SAMEDAY`  | SAMEDAY    |
| `STANDARD` | STANDARD   |