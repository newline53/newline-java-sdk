# AchReturnCodeRequest

The reason an ACH payment is being returned. Required if returning an RDFI transaction. Optional if reversing an ODFI transaction.


## Example Usage

```java
import com.newline53.sdk.models.operations.AchReturnCodeRequest;

AchReturnCodeRequest value = AchReturnCodeRequest.R01;
```


## Values

| Name  | Value |
| ----- | ----- |
| `R01` | R01   |
| `R02` | R02   |
| `R03` | R03   |
| `R04` | R04   |
| `R06` | R06   |
| `R09` | R09   |
| `R10` | R10   |
| `R16` | R16   |
| `R17` | R17   |
| `R20` | R20   |
| `R23` | R23   |
| `R29` | R29   |
| `R31` | R31   |