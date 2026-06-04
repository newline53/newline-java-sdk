# GetTransactionsUidType

Transactions are categorized by Type.  See the Transaction Statuses and Transaction Types section below for a list and definition of each Transaction Type that Newline supports.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsUidType;

GetTransactionsUidType value = GetTransactionsUidType.ACH;

// Open enum: use .of() to create instances from custom string values
GetTransactionsUidType custom = GetTransactionsUidType.of("custom_value");
```


## Values

| Name                     | Value                    |
| ------------------------ | ------------------------ |
| `ACH`                    | ach                      |
| `ACH_RETURN`             | ach_return               |
| `ATM_WITHDRAWAL`         | atm_withdrawal           |
| `BOOK_TRANSFER`          | book_transfer            |
| `CARD_LOAD`              | card_load                |
| `CARD_PURCHASE`          | card_purchase            |
| `CARD_REFUND`            | card_refund              |
| `CASH_LOAD`              | cash_load                |
| `CORPORATE_ACTION`       | corporate_action         |
| `CREDIT`                 | credit                   |
| `DENIED_AUTHORIZATION`   | denied_authorization     |
| `DISPUTE`                | dispute                  |
| `FEE`                    | fee                      |
| `INITIATED_ACH_RETURN`   | initiated_ach_return     |
| `INITIATED_ACH_REVERSAL` | initiated_ach_reversal   |
| `INITIATED_WIRE_RETURN`  | initiated_wire_return    |
| `INSTANT_PAYMENT`        | instant_payment          |
| `INTEREST`               | interest                 |
| `OTHER`                  | other                    |
| `PEER_TO_PEER_TRANSFER`  | peer_to_peer_transfer    |
| `REVERSAL`               | reversal                 |
| `REVERSED_TRANSFER`      | reversed_transfer        |
| `SET_ACCOUNT_BALANCE`    | set_account_balance      |
| `WIRE`                   | wire                     |