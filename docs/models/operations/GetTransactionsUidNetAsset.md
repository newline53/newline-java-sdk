# GetTransactionsUidNetAsset

Indicates whether the Customer's asset has gone up (`positive`), gone down (`negative`) or stayed the same (`neutral`) as a result of this Transaction.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsUidNetAsset;

GetTransactionsUidNetAsset value = GetTransactionsUidNetAsset.POSITIVE;

// Open enum: use .of() to create instances from custom string values
GetTransactionsUidNetAsset custom = GetTransactionsUidNetAsset.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `POSITIVE` | positive   |
| `NEGATIVE` | negative   |
| `NEUTRAL`  | neutral    |