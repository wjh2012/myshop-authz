package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.TemporaryKey.keyPair;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawAccessToken;
import static ggomg.myshopauthz.token.RawTokenMaker.createRawRefreshToken;

import ggomg.myshopauthz.user.User;
import ggomg.myshopauthz.user.UserService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserService userService;

    public String makeAccessToken(Long id) {
        User user = userService.findUser(id)
                .orElseThrow(NoSuchElementException::new);

        return createRawAccessToken(user.getId(), user.getRole());
    }

    public String makeRefreshToken(Long id) {
        User user = userService.findUser(id)
                .orElseThrow(NoSuchElementException::new);

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
            throw new JwtException("Invalid token", e);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid user ID format in token");
        }
    }

}
