# DeniedReason

The reason for the authorization denial. For wire transactions, must be one of (`vrn_archived`), (`vrn_locked`), (`international_payment`), (`unauthorized_credit`). For ACH transactions, must be one of (`vrn_archived`), (`vrn_locked`), (`debit_limit_exceeded`), (`unauthorized_credit`), (`unauthorized_debit`), (`insufficient_funds`)


## Example Usage

```java
import com.newline53.sdk.models.operations.DeniedReason;

DeniedReason value = DeniedReason.DEBIT_LIMIT_EXCEEDED;
```


## Values

| Name                    | Value                   |
| ----------------------- | ----------------------- |
| `DEBIT_LIMIT_EXCEEDED`  | debit_limit_exceeded    |
| `INSUFFICIENT_FUNDS`    | insufficient_funds      |
| `INTERNATIONAL_PAYMENT` | international_payment   |
| `UNAUTHORIZED_CREDIT`   | unauthorized_credit     |
| `UNAUTHORIZED_DEBIT`    | unauthorized_debit      |
| `VRN_ARCHIVED`          | vrn_archived            |
| `VRN_LOCKED`            | vrn_locked              |