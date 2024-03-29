package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.TemporaryKey.keyPair;

import ggomg.myshopauthz.user.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RawTokenMaker {

    private static final Long REFRESH_TOKEN_TIME_SPAN = 60L * 60 * 24 * 30 * 1000; // 한 달
    private static final Long ACCESS_TOKEN_TIME_SPAN = 60L * 60 * 1000; // 한 시간

    public static String createRawRefreshToken(Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                .setHeaderParam("typ", "JWT")

                .setIssuer("myshop-authz") // 발신
                .setAudience("refreshService") // 수신

                .setSubject(id.toString())
                .claim("role", role)

                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(REFRESH_TOKEN_TIME_SPAN)))

                .signWith(keyPair.getPrivate())
                .compact();
    }

    public static String createRawAccessToken(Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                .setHeaderParam("typ", "JWT")

                .setIssuer("myshop-authz") // 발신
                .setAudience("accessService") // 수신

                .setSubject(id.toString())
                .claim("role", role)

                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(ACCESS_TOKEN_TIME_SPAN)))

                .signWith(keyPair.getPrivate())
                .compact();
    }

}
