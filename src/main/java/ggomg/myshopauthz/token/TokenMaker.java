package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.token.key.KeyMaker.keyPair;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenMaker {

    private static final Long ACCESS_TOKEN_TIME_SPAN = 60L * 60 * 1000; // 한 시간
    private static final Long REFRESH_TOKEN_TIME_SPAN = 60L * 60 * 24 * 30 * 1000; // 한 달

    public static String createAccessToken(Long id, String role) {
        return Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                .setHeaderParam("typ", "JWT")
                .setIssuer("myshop-authz") // 발급자
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(ACCESS_TOKEN_TIME_SPAN)))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public static String createRefreshToken(Long id, String role) {
        return Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                .setHeaderParam("typ", "JWT")
                .setIssuer("myshop-authz") // 발급자
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(REFRESH_TOKEN_TIME_SPAN)))
                .signWith(keyPair.getPrivate())
                .compact();
    }
}
