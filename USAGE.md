<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import com.newline53.sdk.NewlineSDK;
import com.newline53.sdk.models.components.Security;
import com.newline53.sdk.models.operations.GenerateAuthTokenResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        NewlineSDK sdk = NewlineSDK.builder()
                .security(Security.builder()
                    .programUid(System.getenv().getOrDefault("PROGRAM_UID", ""))
                    .hmacKey(System.getenv().getOrDefault("HMAC_KEY", ""))
                    .build())
            .build();

        GenerateAuthTokenResponse res = sdk.auth().generateToken()
                .call();

        if (res.object().isPresent()) {
            System.out.println(res.object().get());
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->