# ListCustomersQueryParamStatus

Filter by onboarding status. Please note that the `initiated` enum value will not be respected unless the `include_initiated=true` parameter is also provided. Multiple values are allowed e.g. `status[]=queued&status[]=active`.


## Example Usage

```java
import com.newline53.sdk.models.operations.ListCustomersQueryParamStatus;

ListCustomersQueryParamStatus value = ListCustomersQueryParamStatus.INITIATED;
```


## Values

| Name                | Value               |
| ------------------- | ------------------- |
| `INITIATED`         | initiated           |
| `QUEUED`            | queued              |
| `IDENTITY_VERIFIED` | identity_verified   |
| `ACTIVE`            | active              |
| `MANUAL_REVIEW`     | manual_review       |
| `REJECTED`          | rejected            |
| `PENDING_ARCHIVAL`  | pending_archival    |
| `ARCHIVED`          | archived            |
| `UNDER_REVIEW`      | under_review        |