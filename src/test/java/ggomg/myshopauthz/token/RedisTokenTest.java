package ggomg.myshopauthz.token;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenService;
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
    RefreshTokenService refreshTokenService;

    @Test
    void refresh토큰_저장_조회_성공(){
        String rawRefreshToken = RawTokenMaker.createRawRefreshToken(1L, Role.NORMAL);
        refreshTokenService.saveRefreshToken(1L, rawRefreshToken);

        String foundToken = refreshTokenService.findRefreshTokenByUserId(1L);

        Assertions.assertThat(rawRefreshToken).isEqualTo(foundToken);
    }

    @Test
    void refresh토큰_저장_조회_실패(){
        String rawRefreshToken = RawTokenMaker.createRawRefreshToken(1L, Role.NORMAL);
        refreshTokenService.saveRefreshToken(1L, rawRefreshToken);

        Assertions.assertThatThrownBy(() -> {
            String foundToken = refreshTokenService.findRefreshTokenByUserId(2L);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
