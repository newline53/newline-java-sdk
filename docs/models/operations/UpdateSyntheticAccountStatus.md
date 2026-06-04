# UpdateSyntheticAccountStatus

A value indicating the overall state of this Synthetic Account.


## Example Usage

```java
import com.newline53.sdk.models.operations.UpdateSyntheticAccountStatus;

UpdateSyntheticAccountStatus value = UpdateSyntheticAccountStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
UpdateSyntheticAccountStatus custom = UpdateSyntheticAccountStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `INITIATED` | initiated   |
| `ACTIVE`    | active      |
| `ARCHIVED`  | archived    |
| `FAILED`    | failed      |