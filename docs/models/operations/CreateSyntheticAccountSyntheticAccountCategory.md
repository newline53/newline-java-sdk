# CreateSyntheticAccountSyntheticAccountCategory

The name of the Synthetic Account Category associated with the Synthetic Account Type for this account. Refer to the Synthetic Account Type for details.

## Example Usage

```java
import com.newline53.sdk.models.operations.CreateSyntheticAccountSyntheticAccountCategory;

CreateSyntheticAccountSyntheticAccountCategory value = CreateSyntheticAccountSyntheticAccountCategory.GENERAL;

// Open enum: use .of() to create instances from custom string values
CreateSyntheticAccountSyntheticAccountCategory custom = CreateSyntheticAccountSyntheticAccountCategory.of("custom_value");
```


## Values

| Name                       | Value                      |
| -------------------------- | -------------------------- |
| `GENERAL`                  | general                    |
| `ACH_EXTERNAL`             | ach_external               |
| `INSTANT_PAYMENT_EXTERNAL` | instant_payment_external   |
| `WIRE_EXTERNAL`            | wire_external              |