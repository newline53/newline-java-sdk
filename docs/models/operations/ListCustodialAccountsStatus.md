# ListCustodialAccountsStatus

A value indicating the overall state of this Custodial Account (nullable if account type is asset).


## Example Usage

```java
import com.newline53.sdk.models.operations.ListCustodialAccountsStatus;

ListCustodialAccountsStatus value = ListCustodialAccountsStatus.ACTIVE;

// Open enum: use .of() to create instances from custom string values
ListCustodialAccountsStatus custom = ListCustodialAccountsStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `ACTIVE`   | active     |
| `ARCHIVED` | archived   |