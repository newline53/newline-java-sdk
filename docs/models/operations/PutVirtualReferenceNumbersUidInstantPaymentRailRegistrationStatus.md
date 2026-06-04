# PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus

Registration status with Newline and Fifth Third, for RTP network acceptance.

## Example Usage

```java
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus;

PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus value = PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus.FAILED;

// Open enum: use .of() to create instances from custom string values
PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus custom = PutVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `FAILED`       | failed         |
| `PENDING`      | pending        |
| `REGISTERED`   | registered     |
| `UNREGISTERED` | unregistered   |