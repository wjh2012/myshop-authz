package ggomg.myshopauthz.token.refreshToken;

import static ggomg.myshopauthz.token.key.KeyMaker.keyPair;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final RedisTokenRepository redisTokenRepository;

    public void createRedisToken(Long userId, String refreshToken) {
        RedisToken redisToken = RedisToken.of(userId, refreshToken);

        redisTokenRepository.save(redisToken);
    }

    public String findRefreshTokenById(Long userId) {
        RedisToken redisToken = redisTokenRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        return redisToken.getRefreshToken();
    }

    public String decodeRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(keyPair.getPrivate())
                .requireAudience("refreshService")
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        return claims.getSubject();
    }
}