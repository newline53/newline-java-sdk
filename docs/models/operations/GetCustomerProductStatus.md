# GetCustomerProductStatus

the status of the Customer Product onboarding

## Example Usage

```java
import com.newline53.sdk.models.operations.GetCustomerProductStatus;

GetCustomerProductStatus value = GetCustomerProductStatus.ACTIVE;

// Open enum: use .of() to create instances from custom string values
GetCustomerProductStatus custom = GetCustomerProductStatus.of("custom_value");
```


## Values

| Name            | Value           |
| --------------- | --------------- |
| `ACTIVE`        | active          |
| `CREATED`       | created         |
| `MANUAL_REVIEW` | manual_review   |
| `REJECTED`      | rejected        |