# GetTransactionsUidStatus

When a Transfer is created via [POST /transfers](/reference/post_transfers), an associated Transaction is created with status `queued`. If it involves at least one custodial transfer, once the custodial transfer is initiated, the status will transition to `pending`.

Once a Transaction is settled, whether it has asset movement in custodial level, or is synthetic-only, or is RDFI (Receiving Depository Financial Institution, meaning no Newline Transfer was associated), it will have a `settled` status.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsUidStatus;

GetTransactionsUidStatus value = GetTransactionsUidStatus.CANCELED;

// Open enum: use .of() to create instances from custom string values
GetTransactionsUidStatus custom = GetTransactionsUidStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |
| `DENIED`   | denied     |
| `EXPIRED`  | expired    |