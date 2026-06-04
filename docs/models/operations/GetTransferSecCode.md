# GetTransferSecCode

Standard Entry Class (SEC) code. Newline supports the following SEC codes: CCD, CIE, PPD, TEL, WEB. For more details, refer to our ACH guide's [section](https://developers.newline53.com/docs/ach#standard-entry-class-sec-codes) on SEC code use.


## Example Usage

```java
import com.newline53.sdk.models.operations.GetTransferSecCode;

GetTransferSecCode value = GetTransferSecCode.CCD;

// Open enum: use .of() to create instances from custom string values
GetTransferSecCode custom = GetTransferSecCode.of("custom_value");
```


## Values

| Name  | Value |
| ----- | ----- |
| `CCD` | CCD   |
| `CIE` | CIE   |
| `PPD` | PPD   |
| `TEL` | TEL   |
| `WEB` | WEB   |