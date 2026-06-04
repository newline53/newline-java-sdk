# GetTransactionsNetAsset

Indicates whether the Customer's asset has gone up (`positive`), gone down (`negative`) or stayed the same (`neutral`) as a result of this Transaction.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransactionsNetAsset;

GetTransactionsNetAsset value = GetTransactionsNetAsset.POSITIVE;

// Open enum: use .of() to create instances from custom string values
GetTransactionsNetAsset custom = GetTransactionsNetAsset.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `POSITIVE` | positive   |
| `NEGATIVE` | negative   |
| `NEUTRAL`  | neutral    |