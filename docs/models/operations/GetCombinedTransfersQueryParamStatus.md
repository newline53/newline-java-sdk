# GetCombinedTransfersQueryParamStatus

Filter by status. Multiple values are allowed, e.g. `status[]=queued&status[]=pending`.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetCombinedTransfersQueryParamStatus;

GetCombinedTransfersQueryParamStatus value = GetCombinedTransfersQueryParamStatus.QUEUED;
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `PENDING`   | pending     |
| `FAILED`    | failed      |
| `COMPLETED` | completed   |