package ggomg.myshopauthz.key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.KeyPair;

public class TemporaryKey {
    public static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
}
