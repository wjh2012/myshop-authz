package ggomg.myshopauthz.token;

import ggomg.myshopauthz.token.DTO.CreateTokenRequest;
import ggomg.myshopauthz.token.refreshToken.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenProvideController {
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/newToken")
    public ResponseEntity<AccessRefreshTokenDTO> newToken(@RequestBody CreateTokenRequest request) {

        String accessToken = tokenService.provideAccessToken(request.getId());
        String refreshToken = tokenService.provideRefreshToken(request.getId());

        refreshTokenService.saveRefreshToken(request.getId(), refreshToken);

        AccessRefreshTokenDTO response = new AccessRefreshTokenDTO(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AccessRefreshTokenDTO> refreshToken(@RequestBody CreateTokenRequest request) {

        refreshTokenService.findRefreshTokenById(request.getId());

        String accessToken = tokenService.provideAccessToken(request.getId());
        String refreshToken = tokenService.provideRefreshToken(request.getId());

        AccessRefreshTokenDTO response = new AccessRefreshTokenDTO(accessToken, refreshToken);

        return ResponseEntity.ok(response);
    }

    @Data
    @AllArgsConstructor
    static class AccessRefreshTokenDTO {
        private String accessToken;
        private String refreshToken;
    }
}
