# ForbiddenRequestorType

Type of the customer requesting a return.

## Example Usage

```java
import com.newline53.sdk.models.operations.ForbiddenRequestorType;

ForbiddenRequestorType value = ForbiddenRequestorType.CUSTOMER;

// Open enum: use .of() to create instances from custom string values
ForbiddenRequestorType custom = ForbiddenRequestorType.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CUSTOMER`  | customer    |
| `SUBCLIENT` | subclient   |