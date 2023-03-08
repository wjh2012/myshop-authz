package ggomg.myshopauthz.tokenProvider.keyProvider;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWKey {
    String kid;
    String alg;
    String kty;
    String use;
    String publicKey;
}