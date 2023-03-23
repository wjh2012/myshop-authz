package ggomg.myshopauthz;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenService;
import ggomg.myshopauthz.token.userAuthority.Role;
import ggomg.myshopauthz.token.userAuthority.UserService;
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
            userService.saveUser(1L, Role.NORMAL);
            userService.saveUser(2L, Role.MANAGER);
            userService.saveUser(3L, Role.NORMAL);
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitRedis {

        private final RefreshTokenService refreshTokenService;

        private void dbInit() {
        }
    }


}
