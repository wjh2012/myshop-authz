package ggomg.myshopauthz;

import static ggomg.myshopauthz.token.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.token.refreshToken.RedisTokenService;
import ggomg.myshopauthz.userAuthority.UserService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitMysql initMysql;
    private final InitRedis initRedis;

    @PostConstruct
    public void init() {
        initMysql.dbInit();
        initRedis.dbInit();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitMysql {

        private final UserService userService;

        private void dbInit() {
            userService.createUser(1L, "manager");
            userService.createUser(2L, "normal");
            userService.createUser(3L, "manager");
            userService.createUser(4L, "normal");
            userService.createUser(5L, "manager");
            userService.createUser(6L, "normal");
            userService.createUser(7L, "manager");
            userService.createUser(8L, "normal");
            userService.createUser(9L, "manager");
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitRedis {

        private final RedisTokenService redisTokenService;

        private void dbInit() {
            String refreshToken1 = createRefreshToken(4L, "normal");
            String refreshToken2 = createRefreshToken(5L, "normal");

            redisTokenService.createRedisToken(4L, refreshToken1);
            redisTokenService.createRedisToken(5L, refreshToken2);
        }
    }


}
