package ggomg.myshopauthz.key;

import static ggomg.myshopauthz.key.TemporaryKey.keyPair;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyProvideController {

    @GetMapping("/publicKey")
    public ResponseEntity<JWKS<List<JWKey>>> distributeKey() {
        String key = Base64
                .getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());

        JWKey jwKey = JWKey.builder()
                .kid(UUID.randomUUID().toString())
                .alg(keyPair.getPublic().getAlgorithm())
                .kty("RSA")
                .use("enc")
                .publicKey(key)
                .build();

        List<JWKey> collect = new ArrayList<>();
        collect.add(jwKey);

        return ResponseEntity.ok().body(new JWKS<>(collect));
    }

    @Data
    @AllArgsConstructor
    static class JWKS<T> {
        private T keys;
    }

}
