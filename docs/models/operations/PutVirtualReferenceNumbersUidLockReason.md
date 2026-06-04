# PutVirtualReferenceNumbersUidLockReason

Provided reason for locking the VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockReason;

PutVirtualReferenceNumbersUidLockReason value = PutVirtualReferenceNumbersUidLockReason.ADMIN;

// Open enum: use .of() to create instances from custom string values
PutVirtualReferenceNumbersUidLockReason custom = PutVirtualReferenceNumbersUidLockReason.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ADMIN`            | admin              |
| `CUSTOMER_REQUEST` | customer_request   |