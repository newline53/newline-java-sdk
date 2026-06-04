# GetTransactionEventsTypeSettled

## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionEventsTypeSettled;

GetTransactionEventsTypeSettled value = GetTransactionEventsTypeSettled.ODFI_ACH_DEPOSIT;

// Open enum: use .of() to create instances from custom string values
GetTransactionEventsTypeSettled custom = GetTransactionEventsTypeSettled.of("custom_value");
```


## Values

| Name                  | Value                 |
| --------------------- | --------------------- |
| `ODFI_ACH_DEPOSIT`    | odfi_ach_deposit      |
| `ODFI_ACH_WITHDRAWAL` | odfi_ach_withdrawal   |
| `RDFI_ACH_DEPOSIT`    | rdfi_ach_deposit      |
| `RDFI_ACH_WITHDRAWAL` | rdfi_ach_withdrawal   |