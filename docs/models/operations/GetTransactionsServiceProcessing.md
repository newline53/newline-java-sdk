# GetTransactionsServiceProcessing

Requested service processing duration for the ACH transfer. Allowable values are: STANDARD or SAMEDAY


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsServiceProcessing;

GetTransactionsServiceProcessing value = GetTransactionsServiceProcessing.SAMEDAY;

// Open enum: use .of() to create instances from custom string values
GetTransactionsServiceProcessing custom = GetTransactionsServiceProcessing.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `SAMEDAY`  | SAMEDAY    |
| `STANDARD` | STANDARD   |