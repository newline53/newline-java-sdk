# GetVirtualReferenceNumbersUidLockReason

Provided reason for locking the VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersUidLockReason;

GetVirtualReferenceNumbersUidLockReason value = GetVirtualReferenceNumbersUidLockReason.ADMIN;

// Open enum: use .of() to create instances from custom string values
GetVirtualReferenceNumbersUidLockReason custom = GetVirtualReferenceNumbersUidLockReason.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ADMIN`            | admin              |
| `CUSTOMER_REQUEST` | customer_request   |