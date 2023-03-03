package ggomg.myshopauthz;

import static ggomg.myshopauthz.tokenProvider.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.tokenProvider.refreshToken.RefreshTokenService;
import ggomg.myshopauthz.tokenProvider.userAuthority.UserService;
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
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitRedis {

        private final RefreshTokenService refreshTokenService;

        private void dbInit() {
            refreshTokenService.saveRefreshToken(8L, createRefreshToken(8L, "normal"));
            refreshTokenService.saveRefreshToken(9L, createRefreshToken(9L, "manager"));
        }
    }


}
