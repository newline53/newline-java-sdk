# GetTransactionsQueryParamStatus

Filter by status. Multiple values are allowed, e.g. `status[]=queued&status[]=pending`.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsQueryParamStatus;

GetTransactionsQueryParamStatus value = GetTransactionsQueryParamStatus.CANCELED;
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CANCELED` | canceled   |
| `QUEUED`   | queued     |
| `PENDING`  | pending    |
| `SETTLED`  | settled    |
| `FAILED`   | failed     |
| `EXPIRED`  | expired    |
| `DENIED`   | denied     |