# GetVirtualReferenceNumbersLockReason

Provided reason for locking the VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.GetVirtualReferenceNumbersLockReason;

GetVirtualReferenceNumbersLockReason value = GetVirtualReferenceNumbersLockReason.ADMIN;

// Open enum: use .of() to create instances from custom string values
GetVirtualReferenceNumbersLockReason custom = GetVirtualReferenceNumbersLockReason.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ADMIN`            | admin              |
| `CUSTOMER_REQUEST` | customer_request   |