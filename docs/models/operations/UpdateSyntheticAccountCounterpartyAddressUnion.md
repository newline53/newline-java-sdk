# UpdateSyntheticAccountCounterpartyAddressUnion

Address of the business or individual who owns the external account. The accepted format depends on your program's wire address configuration (`unstructured`, `structured`, or `both`). Unstructured format uses `line1`/`line2`/`line3`/`country`. Structured format uses `building_number`/`street_name`/`city`/`postal_code`/`state`/`country` (with `city` and `country` required).



## Supported Types

### [`UpdateSyntheticAccountCounterpartyAddressUnstructuredAddress`](../../models/operations/UpdateSyntheticAccountCounterpartyAddressUnstructuredAddress.md)

```java
UpdateSyntheticAccountCounterpartyAddressUnion value = UpdateSyntheticAccountCounterpartyAddressUnion.of(UpdateSyntheticAccountCounterpartyAddressUnstructuredAddress.builder()
    .line1("123 Abc St.")
    .line2("Boring, Oregon 97009")
    .line3(null)
    .country(null)
    .build());
```

### [`UpdateSyntheticAccountStructuredAddress`](../../models/operations/UpdateSyntheticAccountStructuredAddress.md)

```java
UpdateSyntheticAccountCounterpartyAddressUnion value = UpdateSyntheticAccountCounterpartyAddressUnion.of(UpdateSyntheticAccountStructuredAddress.builder()
    .city("Cincinnati")
    .country("US")
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
if (value.updateSyntheticAccountCounterpartyAddressUnstructuredAddress().isPresent()) {
    com.newline53.sdk.models.operations.UpdateSyntheticAccountCounterpartyAddressUnstructuredAddress updateSyntheticAccountCounterpartyAddressUnstructuredAddressValue = value.updateSyntheticAccountCounterpartyAddressUnstructuredAddress().get();
    // Handle updateSyntheticAccountCounterpartyAddressUnstructuredAddress variant
} else if (value.updateSyntheticAccountStructuredAddress().isPresent()) {
    com.newline53.sdk.models.operations.UpdateSyntheticAccountStructuredAddress updateSyntheticAccountStructuredAddressValue = value.updateSyntheticAccountStructuredAddress().get();
    // Handle updateSyntheticAccountStructuredAddress variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
