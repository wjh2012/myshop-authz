package ggomg.myshopauthz.token.key;

import static ggomg.myshopauthz.token.key.KeyMaker.keyPair;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
    public Result distributeKey() {
        String key = Base64
                .getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());

        JWKey jwKey = JWKey.builder()
                .kid("123")
                .alg("RS256")
                .kty("RSA")
                .use("enc")
                .n(key)
                .build();

        List<JWKey> collect = new ArrayList<>();
        collect.add(jwKey);

        return new Result(collect);
    }

    @Data
    @Builder
    static class JWKey {
        String kid;
        String alg;
        String kty;
        String use;
        String n;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T keys;
    }

}
