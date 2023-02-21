package ggomg.myshopauthz.token.refreshToken;

import static ggomg.myshopauthz.token.key.KeyMaker.keyPair;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
                .setSigningKey(keyPair.getPrivate())
                .requireAudience("refreshService")
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        return  claims.getSubject();
    }
}