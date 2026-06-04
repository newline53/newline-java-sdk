# ListSyntheticAccountsDataStatus

A value indicating the overall state of this Synthetic Account.


## Example Usage

```java
import com.newline53.sdk.models.operations.ListSyntheticAccountsDataStatus;

ListSyntheticAccountsDataStatus value = ListSyntheticAccountsDataStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
ListSyntheticAccountsDataStatus custom = ListSyntheticAccountsDataStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `INITIATED` | initiated   |
| `ACTIVE`    | active      |
| `ARCHIVED`  | archived    |
| `FAILED`    | failed      |