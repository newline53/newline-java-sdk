# GetCustodialLineItemsUidStatus

Other statuses such as `pending` will be available later

## Example Usage

```java
import com.newline53.sdk.models.operations.GetCustodialLineItemsUidStatus;

GetCustodialLineItemsUidStatus value = GetCustodialLineItemsUidStatus.SETTLED;

// Open enum: use .of() to create instances from custom string values
GetCustodialLineItemsUidStatus custom = GetCustodialLineItemsUidStatus.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `SETTLED` | settled   |
| `VOIDED`  | voided    |