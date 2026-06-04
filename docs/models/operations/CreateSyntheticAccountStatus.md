# CreateSyntheticAccountStatus

A value indicating the overall state of this Synthetic Account.


## Example Usage

```java
import com.newline53.sdk.models.operations.CreateSyntheticAccountStatus;

CreateSyntheticAccountStatus value = CreateSyntheticAccountStatus.INITIATED;

// Open enum: use .of() to create instances from custom string values
CreateSyntheticAccountStatus custom = CreateSyntheticAccountStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `INITIATED` | initiated   |
| `ACTIVE`    | active      |
| `ARCHIVED`  | archived    |
| `FAILED`    | failed      |