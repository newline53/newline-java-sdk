# PostCombinedTransfersWireTransmitter

Information about the Transmitter. Must be provided if the `initiator_type` is `transmitter`. Includes the transmitter's name, identifier, and address. The accepted address format depends on your program's wire address configuration. For `unstructured` format: `line1` and `country` are required. For `structured` format: `city` and `country` are required.



## Supported Types

### [`WireTransmitterTransferUnstructuredAddress`](../../models/operations/WireTransmitterTransferUnstructuredAddress.md)

```java
PostCombinedTransfersWireTransmitter value = PostCombinedTransfersWireTransmitter.of(WireTransmitterTransferUnstructuredAddress.builder()
    .name("Marge's Roofing Inc")
    .transmitterIdentifier("123456789012345")
    .line1("123 Abc St.")
    .line2("Boring, Oregon 97009")
    .line3(null)
    .country("US")
    .build());
```

### [`TransferStructuredAddress`](../../models/operations/TransferStructuredAddress.md)

```java
PostCombinedTransfersWireTransmitter value = PostCombinedTransfersWireTransmitter.of(TransferStructuredAddress.builder()
    .city("Cincinnati")
    .country("US")
    .name("Marge's Roofing Inc")
    .transmitterIdentifier("123456789012345")
    .line1("123 Main St")
    .line2("Suite 400")
    .buildingNumber("123")
    .streetName("Main St")
    .state("OH")
    .postalCode("45202")
    .build());
```

## Consumption Patterns

### Java 11+ (Accessor Methods)

```java
if (value.wireTransmitterTransferUnstructuredAddress().isPresent()) {
    com.newline53.sdk.models.operations.WireTransmitterTransferUnstructuredAddress wireTransmitterTransferUnstructuredAddressValue = value.wireTransmitterTransferUnstructuredAddress().get();
    // Handle wireTransmitterTransferUnstructuredAddress variant
} else if (value.transferStructuredAddress().isPresent()) {
    com.newline53.sdk.models.operations.TransferStructuredAddress transferStructuredAddressValue = value.transferStructuredAddress().get();
    // Handle transferStructuredAddress variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
