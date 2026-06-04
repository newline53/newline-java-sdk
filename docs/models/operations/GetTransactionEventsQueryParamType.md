# GetTransactionEventsQueryParamType

Filter by type. Multiple values are allowed, e.g. `type[]=odfi_ach_deposit&type[]=odfi_ach_withdrawal`.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionEventsQueryParamType;

GetTransactionEventsQueryParamType value = GetTransactionEventsQueryParamType.ODFI_ACH_DEPOSIT;
```


## Values

| Name                  | Value                 |
| --------------------- | --------------------- |
| `ODFI_ACH_DEPOSIT`    | odfi_ach_deposit      |
| `ODFI_ACH_WITHDRAWAL` | odfi_ach_withdrawal   |
| `RDFI_ACH_DEPOSIT`    | rdfi_ach_deposit      |
| `RDFI_ACH_WITHDRAWAL` | rdfi_ach_withdrawal   |