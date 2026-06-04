# PostReturnsRequestorTypeResponse

Type of the customer requesting a return.

## Example Usage

```java
import com.newline53.sdk.models.operations.PostReturnsRequestorTypeResponse;

PostReturnsRequestorTypeResponse value = PostReturnsRequestorTypeResponse.CUSTOMER;

// Open enum: use .of() to create instances from custom string values
PostReturnsRequestorTypeResponse custom = PostReturnsRequestorTypeResponse.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CUSTOMER`  | customer    |
| `SUBCLIENT` | subclient   |