package ggomg.myshopauthz.token.refreshToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.net.URL;
import java.net.URLConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long userId, String token) {
        refreshTokenRepository.save(RefreshToken.of(userId, token));
    }

    public String decodeRefreshToken(String refreshToken) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey()
                .requireAudience("refreshService")
                .build()
                .parse(refreshToken)
                .getBody();

        String subject = claims.getSubject();
    }
}