# GetTransactionsUidPaymentType

Recurring (R), single entry (S), or standing authorization entry (ST). If no value is provided, the default value populated in the NACHA file is for a single entry.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsUidPaymentType;

GetTransactionsUidPaymentType value = GetTransactionsUidPaymentType.R;

// Open enum: use .of() to create instances from custom string values
GetTransactionsUidPaymentType custom = GetTransactionsUidPaymentType.of("custom_value");
```


## Values

| Name  | Value |
| ----- | ----- |
| `R`   | R     |
| `S`   | S     |
| `ST`  | ST    |