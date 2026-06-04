# GetCustodialLineItemsDataStatus

Other statuses such as `pending` will be available later

## Example Usage

```java
import com.newline53.sdk.models.operations.GetCustodialLineItemsDataStatus;

GetCustodialLineItemsDataStatus value = GetCustodialLineItemsDataStatus.SETTLED;

// Open enum: use .of() to create instances from custom string values
GetCustodialLineItemsDataStatus custom = GetCustodialLineItemsDataStatus.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `SETTLED` | settled   |
| `VOIDED`  | voided    |