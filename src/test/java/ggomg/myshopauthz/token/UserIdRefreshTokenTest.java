package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.token.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.token.refreshToken.RefreshTokenRepository;
import ggomg.myshopauthz.token.refreshToken.RefreshTokenService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserIdRefreshTokenTest {

    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void refresh토큰발급(){
        refreshTokenRepository.deleteAll();

        String refreshToken = createRefreshToken(1L,"normal");

        refreshTokenService.saveRefreshToken(1L, refreshToken);

        String result = refreshTokenService.findRefreshTokenById(1L);

        System.out.println("result = " + result);
        System.out.println("refreshToken = " + refreshToken);

        Assertions.assertThat(result).isEqualTo(refreshToken);
    }
}
