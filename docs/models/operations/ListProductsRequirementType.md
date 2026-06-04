# ListProductsRequirementType

The type of the response values for the requirement

## Example Usage

```java
import com.newline53.sdk.models.operations.ListProductsRequirementType;

ListProductsRequirementType value = ListProductsRequirementType.FIXED_LIST;

// Open enum: use .of() to create instances from custom string values
ListProductsRequirementType custom = ListProductsRequirementType.of("custom_value");
```


## Values

| Name              | Value             |
| ----------------- | ----------------- |
| `FIXED_LIST`      | fixed_list        |
| `INTEGER_GREATER` | integer_greater   |
| `ORDERED_LIST`    | ordered_list      |
| `NUMBER`          | number            |
| `STRING`          | string            |