package ggomg.myshopauthz.tokenProvider;

import ggomg.myshopauthz.tokenProvider.controllerDTO.CreateTokenRequest;
import ggomg.myshopauthz.tokenProvider.refreshToken.RefreshTokenService;
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
    private final TokenProvideService tokenProvideService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/newToken")
    public ResponseEntity<AccessRefreshTokenDTO> newToken(@RequestBody CreateTokenRequest request) {

        String accessToken = tokenProvideService.provideAccessToken(request.getId());
        String refreshToken = tokenProvideService.provideRefreshToken(request.getId());

        refreshTokenService.saveRefreshToken(request.getId(), refreshToken);

        AccessRefreshTokenDTO response = new AccessRefreshTokenDTO(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AccessRefreshTokenDTO> refreshToken(@RequestBody CreateTokenRequest request) {

        refreshTokenService.findRefreshTokenById(request.getId());

        String accessToken = tokenProvideService.provideAccessToken(request.getId());
        String refreshToken = tokenProvideService.provideRefreshToken(request.getId());

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
