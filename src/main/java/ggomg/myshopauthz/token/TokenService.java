package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.key.KeyMaker.keyPair;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    public String create() {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","RS256")
                .setIssuer("myshop-authz") // 발급자
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(keyPair.getPrivate())
                .compact();
    }

}
