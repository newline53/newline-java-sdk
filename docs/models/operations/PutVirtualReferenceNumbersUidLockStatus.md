# PutVirtualReferenceNumbersUidLockStatus

A value indicating the overall state of this VRN.

## Example Usage

```java
import com.newline53.sdk.models.operations.PutVirtualReferenceNumbersUidLockStatus;

PutVirtualReferenceNumbersUidLockStatus value = PutVirtualReferenceNumbersUidLockStatus.ACTIVE;

// Open enum: use .of() to create instances from custom string values
PutVirtualReferenceNumbersUidLockStatus custom = PutVirtualReferenceNumbersUidLockStatus.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `ACTIVE`   | active     |
| `ARCHIVED` | archived   |