package ggomg.myshopauthz.token;

import ggomg.myshopauthz.token.DTO.AccessRefreshTokenDTO;
import ggomg.myshopauthz.token.DTO.AccessTokenDTO;
import ggomg.myshopauthz.token.DTO.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenProvideController {
    private final TokenService tokenService;

    @PostMapping("/newToken")
    public AccessRefreshTokenDTO newToken(@RequestBody TokenRequest request) {
        String accessToken = tokenService.provideAccessToken(request.getId());
        String refreshToken = tokenService.provideRefreshToken(request.getId());

        return new AccessRefreshTokenDTO(accessToken, refreshToken);
    }

    @PostMapping("/accessToken")
    public AccessTokenDTO accessToken(@RequestBody TokenRequest request) {
        return new AccessTokenDTO(tokenService.provideAccessToken(request.getId()));
    }
}
