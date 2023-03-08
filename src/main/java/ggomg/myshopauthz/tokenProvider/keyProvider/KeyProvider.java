package ggomg.myshopauthz.tokenProvider.keyProvider;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.KeyPair;
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
public class KeyProvider {

    static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    @GetMapping("/publicKey")
    public ResponseEntity<Result<List<JWKey>>> distributeKey() {
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

        return ResponseEntity.ok().body(new Result<>(collect));
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T keys;
    }

}
