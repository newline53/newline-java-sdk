# PostVirtualReferenceNumbersLockReason

Provided reason for locking the VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.PostVirtualReferenceNumbersLockReason;

PostVirtualReferenceNumbersLockReason value = PostVirtualReferenceNumbersLockReason.ADMIN;

// Open enum: use .of() to create instances from custom string values
PostVirtualReferenceNumbersLockReason custom = PostVirtualReferenceNumbersLockReason.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ADMIN`            | admin              |
| `CUSTOMER_REQUEST` | customer_request   |