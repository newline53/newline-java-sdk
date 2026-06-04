# GetTransactionEventsUidNetAsset

Indicates whether the Customer's asset has gone up (`positive`), gone down (`negative`) or stayed the same (`neutral`) as a result of this Transaction Event. This value is determined by `type`.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionEventsUidNetAsset;

GetTransactionEventsUidNetAsset value = GetTransactionEventsUidNetAsset.POSITIVE;

// Open enum: use .of() to create instances from custom string values
GetTransactionEventsUidNetAsset custom = GetTransactionEventsUidNetAsset.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `POSITIVE` | positive   |
| `NEGATIVE` | negative   |
| `NEUTRAL`  | neutral    |