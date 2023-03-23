package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.TemporaryKey.keyPair;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawAccessToken;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawRefreshToken;

import ggomg.myshopauthz.token.userAuthority.User;
import ggomg.myshopauthz.token.userAuthority.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    public String provideAccessToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createRawAccessToken(user.getId(), user.getRole());
    }

    public String provideRefreshToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createRawRefreshToken(user.getId(), user.getRole());
    }

    private String getUserIdFromRefreshToken(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .requireAudience("refreshService")
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();
    }

}
