package ggomg.myshopauthz.token.refreshToken;

import static ggomg.myshopauthz.key.KeyMaker.keyPair;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long userId, String refreshToken) {
        UserIdRefreshToken userIdRefreshToken = UserIdRefreshToken.of(userId, refreshToken);

        refreshTokenRepository.save(userIdRefreshToken);
    }

    public String findRefreshTokenById(Long userId) {
        UserIdRefreshToken userIdRefreshToken = refreshTokenRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);

        return userIdRefreshToken.getRefreshToken();
    }

    public String getSubjectFromRefreshToken(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPrivate())
                .requireAudience("refreshService")
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();
    }
}