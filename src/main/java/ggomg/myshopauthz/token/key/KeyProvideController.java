package ggomg.myshopauthz.token.key;

import static ggomg.myshopauthz.token.key.KeyMaker.keyPair;

import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyProvideController {

    @GetMapping("/publicKey")
    public JWKey distributeKey() {
        String key = Base64
                .getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());

        return JWKey.builder()
                .kid("123")
                .alg("RS256")
                .kty("RSA")
                .use("enc")
                .n(key.toString())
                .build();
    }

    @Data
    @Builder
    static class JWKey{
        String kid;
        String alg;
        String kty;
        String use;
        String n;
    }
}
