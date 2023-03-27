package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.TemporaryKey.keyPair;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawAccessToken;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawRefreshToken;

import ggomg.myshopauthz.token.userAuthority.User;
import ggomg.myshopauthz.token.userAuthority.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    public String makeAccessToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createRawAccessToken(user.getId(), user.getRole());
    }

    public String makeRefreshToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createRawRefreshToken(user.getId(), user.getRole());
    }


    public Long parseUserIdFromToken(String token) {
        try {
            String subject = Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .requireAudience("refreshService")
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return Long.parseLong(subject);
        } catch (JwtException e) {
            throw new JwtException("Invalid token or parsing error occurred", e);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid user ID format in token");
        }
    }

}
