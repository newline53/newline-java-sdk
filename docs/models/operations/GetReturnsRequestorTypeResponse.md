# GetReturnsRequestorTypeResponse

Type of the customer requesting a return.

## Example Usage

```java
import com.newline53.sdk.models.operations.GetReturnsRequestorTypeResponse;

GetReturnsRequestorTypeResponse value = GetReturnsRequestorTypeResponse.CUSTOMER;

// Open enum: use .of() to create instances from custom string values
GetReturnsRequestorTypeResponse custom = GetReturnsRequestorTypeResponse.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CUSTOMER`  | customer    |
| `SUBCLIENT` | subclient   |