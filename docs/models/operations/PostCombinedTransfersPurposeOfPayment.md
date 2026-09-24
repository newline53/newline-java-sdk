# PostCombinedTransfersPurposeOfPayment

An optional code supplied when an instant payment Transfer is initiated, indicating the kind of transaction being sent. If supplied, it must be one of the approved codes listed below, otherwise the Transfer is rejected. Omit the field or send an empty string to leave it unset; when unset it is returned as an empty string. Only applies to Newline initiated instant payments; it is not populated for received instant payments.


## Example Usage

```java
import com.newline53.sdk.models.operations.PostCombinedTransfersPurposeOfPayment;

PostCombinedTransfersPurposeOfPayment value = PostCombinedTransfersPurposeOfPayment.NOWS;
```


## Values

| Name    | Value   |
| ------- | ------- |
| `NOWS`  | NOWS    |
| `GDDS`  | GDDS    |
| `SCVE`  | SCVE    |
| `INSC`  | INSC    |
| `INSM`  | INSM    |
| `INVS`  | INVS    |
| `PAYR`  | PAYR    |
| `UBIL`  | UBIL    |
| `PDEP`  | PDEP    |
| `ACCT`  | ACCT    |
| `CBLK`  | CBLK    |
| `MP2_P` | MP2P    |