package ggomg.myshopauthz.token;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenStoreService;
import ggomg.myshopauthz.token.userAuthority.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RedisTokenTest {

    @Autowired
    RefreshTokenStoreService refreshTokenStoreService;

    @Test
    void refresh토큰_저장_조회_성공(){
        String rawRefreshToken = RawTokenMaker.createRawRefreshToken(1L, Role.NORMAL);
        refreshTokenStoreService.saveRefreshToken(1L, rawRefreshToken);

        refreshTokenStoreService.validateToken(1L, rawRefreshToken);
    }

    @Test
    void refresh토큰_저장_조회_실패(){
        String rawRefreshToken = RawTokenMaker.createRawRefreshToken(1L, Role.NORMAL);
        refreshTokenStoreService.saveRefreshToken(1L, rawRefreshToken);

        Assertions.assertThatThrownBy(() -> {
            refreshTokenStoreService.validateToken(2L, rawRefreshToken);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
