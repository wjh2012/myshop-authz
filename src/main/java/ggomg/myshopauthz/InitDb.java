package ggomg.myshopauthz;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenStoreService;
import ggomg.myshopauthz.user.Role;
import ggomg.myshopauthz.user.UserService;
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
            userService.createUser(1L, Role.NORMAL);
            userService.createUser(2L, Role.MANAGER);
            userService.createUser(3L, Role.MANAGER);
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitRedis {

        private final RefreshTokenStoreService refreshTokenStoreService;

        private void dbInit() {
        }
    }


}
