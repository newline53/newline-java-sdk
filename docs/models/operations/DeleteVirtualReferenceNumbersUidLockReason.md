# DeleteVirtualReferenceNumbersUidLockReason

Provided reason for locking the VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.DeleteVirtualReferenceNumbersUidLockReason;

DeleteVirtualReferenceNumbersUidLockReason value = DeleteVirtualReferenceNumbersUidLockReason.ADMIN;

// Open enum: use .of() to create instances from custom string values
DeleteVirtualReferenceNumbersUidLockReason custom = DeleteVirtualReferenceNumbersUidLockReason.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ADMIN`            | admin              |
| `CUSTOMER_REQUEST` | customer_request   |