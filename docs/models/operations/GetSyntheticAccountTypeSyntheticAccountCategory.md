# GetSyntheticAccountTypeSyntheticAccountCategory

The name of the Synthetic Account Category for this type. Accounts can be in one of several categories that indicate their handling properties and defining characteristics such as 'general' or 'external'. As an example, 'external' accounts do not actually hold any assets and are instead used to represent an account at an external institution for use in initiating transfers.

## Example Usage

```java
import com.newline53.sdk.models.operations.GetSyntheticAccountTypeSyntheticAccountCategory;

GetSyntheticAccountTypeSyntheticAccountCategory value = GetSyntheticAccountTypeSyntheticAccountCategory.GENERAL;

// Open enum: use .of() to create instances from custom string values
GetSyntheticAccountTypeSyntheticAccountCategory custom = GetSyntheticAccountTypeSyntheticAccountCategory.of("custom_value");
```


## Values

| Name                       | Value                      |
| -------------------------- | -------------------------- |
| `GENERAL`                  | general                    |
| `ACH_EXTERNAL`             | ach_external               |
| `INSTANT_PAYMENT_EXTERNAL` | instant_payment_external   |
| `WIRE_EXTERNAL`            | wire_external              |