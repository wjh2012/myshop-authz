package ggomg.myshopauthz;

import ggomg.myshopauthz.user.UserService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final UserService userService;

        private void dbInit1() {
            userService.createUser(1L,"manager");
            userService.createUser(2L,"normal");
            userService.createUser(3L,"manager");
            userService.createUser(4L,"normal");
            userService.createUser(5L,"manager");
            userService.createUser(6L,"normal");
            userService.createUser(7L,"manager");
            userService.createUser(8L,"normal");
            userService.createUser(9L,"manager");
        }
    }
}
