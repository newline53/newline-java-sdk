# UpdateSyntheticAccountSyntheticAccountCategory

The name of the Synthetic Account Category associated with the Synthetic Account Type for this account. Refer to the Synthetic Account Type for details.

## Example Usage

```java
import com.newline53.sdk.models.operations.UpdateSyntheticAccountSyntheticAccountCategory;

UpdateSyntheticAccountSyntheticAccountCategory value = UpdateSyntheticAccountSyntheticAccountCategory.GENERAL;

// Open enum: use .of() to create instances from custom string values
UpdateSyntheticAccountSyntheticAccountCategory custom = UpdateSyntheticAccountSyntheticAccountCategory.of("custom_value");
```


## Values

| Name                        | Value                       |
| --------------------------- | --------------------------- |
| `GENERAL`                   | general                     |
| `ACH_EXTERNAL`              | ach_external                |
| `INSTANT_PAYMENTS_EXTERNAL` | instant_payments_external   |
| `WIRE_EXTERNAL`             | wire_external               |