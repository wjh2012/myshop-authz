package ggomg.myshopauthz.token.key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.KeyPair;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KeyMaker {
    public static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
}
