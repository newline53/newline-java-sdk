# GetTransactionsQueryParamType

Filter by type. Multiple values are allowed, e.g. `type[]=dispute&type[]=fee`.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsQueryParamType;

GetTransactionsQueryParamType value = GetTransactionsQueryParamType.ACH;
```


## Values

| Name                    | Value                   |
| ----------------------- | ----------------------- |
| `ACH`                   | ach                     |
| `ACH_RETURN`            | ach_return              |
| `ATM_WITHDRAWAL`        | atm_withdrawal          |
| `BOOK_TRANSFER`         | book_transfer           |
| `CARD_LOAD`             | card_load               |
| `CARD_PURCHASE`         | card_purchase           |
| `CARD_REFUND`           | card_refund             |
| `CASH_LOAD`             | cash_load               |
| `CORPORATE_ACTION`      | corporate_action        |
| `CREDIT`                | credit                  |
| `DENIED_AUTHORIZATION`  | denied_authorization    |
| `DISPUTE`               | dispute                 |
| `FEE`                   | fee                     |
| `INSTANT_PAYMENT`       | instant_payment         |
| `INTEREST`              | interest                |
| `OTHER`                 | other                   |
| `PEER_TO_PEER_TRANSFER` | peer_to_peer_transfer   |
| `REVERSAL`              | reversal                |
| `REVERSED_TRANSFER`     | reversed_transfer       |
| `WIRE`                  | wire                    |