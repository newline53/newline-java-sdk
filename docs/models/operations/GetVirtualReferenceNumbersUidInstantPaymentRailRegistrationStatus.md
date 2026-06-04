# GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus

Registration status with Newline and Fifth Third, for RTP network acceptance.

## Example Usage

```java
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus;

GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus value = GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus.FAILED;

// Open enum: use .of() to create instances from custom string values
GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus custom = GetVirtualReferenceNumbersUidInstantPaymentRailRegistrationStatus.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `FAILED`       | failed         |
| `PENDING`      | pending        |
| `REGISTERED`   | registered     |
| `UNREGISTERED` | unregistered   |