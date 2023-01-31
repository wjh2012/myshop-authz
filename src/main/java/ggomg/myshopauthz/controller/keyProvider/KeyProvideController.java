package ggomg.myshopauthz.controller.keyProvider;

import static ggomg.myshopauthz.key.KeyMaker.keyPair;

import java.security.PublicKey;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyProvideController {

    @GetMapping("/publicKey")
    public String distributeKey(){
        PublicKey key = keyPair.getPublic();
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
