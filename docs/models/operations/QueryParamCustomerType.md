# QueryParamCustomerType

Only return Customers with a customer type matching exactly what is submitted. Multiple values are allowed e.g. `customer_type[]=primary&customer_type[]=secondary`.


## Example Usage

```java
import com.newline53.sdk.models.operations.QueryParamCustomerType;

QueryParamCustomerType value = QueryParamCustomerType.PRIMARY;
```


## Values

| Name              | Value             |
| ----------------- | ----------------- |
| `PRIMARY`         | primary           |
| `SECONDARY`       | secondary         |
| `SOLE_PROPRIETOR` | sole_proprietor   |