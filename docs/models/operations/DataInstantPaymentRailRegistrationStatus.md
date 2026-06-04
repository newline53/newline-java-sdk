# DataInstantPaymentRailRegistrationStatus

Registration status with Newline and Fifth Third, for RTP network acceptance.

## Example Usage

```java
import com.newline53.sdk.models.operations.DataInstantPaymentRailRegistrationStatus;

DataInstantPaymentRailRegistrationStatus value = DataInstantPaymentRailRegistrationStatus.FAILED;

// Open enum: use .of() to create instances from custom string values
DataInstantPaymentRailRegistrationStatus custom = DataInstantPaymentRailRegistrationStatus.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `FAILED`       | failed         |
| `PENDING`      | pending        |
| `REGISTERED`   | registered     |
| `UNREGISTERED` | unregistered   |