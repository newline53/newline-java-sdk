# GetProductRequirementType

The type of the response values for the requirement

## Example Usage

```java
import com.newline53.sdk.models.operations.GetProductRequirementType;

GetProductRequirementType value = GetProductRequirementType.FIXED_LIST;

// Open enum: use .of() to create instances from custom string values
GetProductRequirementType custom = GetProductRequirementType.of("custom_value");
```


## Values

| Name              | Value             |
| ----------------- | ----------------- |
| `FIXED_LIST`      | fixed_list        |
| `INTEGER_GREATER` | integer_greater   |
| `ORDERED_LIST`    | ordered_list      |
| `NUMBER`          | number            |
| `STRING`          | string            |