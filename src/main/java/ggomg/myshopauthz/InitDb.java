package ggomg.myshopauthz;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenStoreService;
import ggomg.myshopauthz.token.userAuthority.UserService;
import ggomg.myshopauthz.token.userAuthority.role.Role;
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
            userService.saveUser(3L, Role.MANAGER);
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
