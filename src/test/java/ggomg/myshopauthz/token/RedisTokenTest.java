package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.token.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.token.refreshToken.RedisTokenRepository;
import ggomg.myshopauthz.token.refreshToken.RedisTokenService;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RedisTokenTest {

    @Autowired
    RedisTokenService redisTokenService;
    @Autowired
    private RedisTokenRepository redisTokenRepository;

    @Test
    void refresh토큰발급(){
        redisTokenRepository.deleteAll();

        String refreshToken = createRefreshToken(1L,"normal");

        redisTokenService.createRedisToken(1L, refreshToken);

        String result = redisTokenService.findRefreshTokenById(1L);

        System.out.println("result = " + result);
        System.out.println("refreshToken = " + refreshToken);

        Assertions.assertThat(result).isEqualTo(refreshToken);
    }
}
