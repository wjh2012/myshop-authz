package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.KeyMaker.keyPair;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TokenMaker {

    private static final Long ACCESS_TOKEN_TIME_SPAN = 60L * 60 * 1000; // 한 시간
    private static final Long REFRESH_TOKEN_TIME_SPAN = 60L * 60 * 24 * 30 * 1000; // 한 달

    public String createAccessToken() {
        return Jwts.builder()
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setIssuer("myshop-authz") // 발급자
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME_SPAN))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setIssuer("myshop-authz") // 발급자
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME_SPAN))
                .signWith(keyPair.getPrivate())
                .compact();
    }
}
