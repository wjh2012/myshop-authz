package ggomg.myshopauthz.token;

import ggomg.myshopauthz.token.RequestResponseDTO.CreateTokenRequest;
import ggomg.myshopauthz.token.RequestResponseDTO.RefreshTokenRequest;
import ggomg.myshopauthz.token.refreshToken.RefreshTokenStoreService;
import io.jsonwebtoken.JwtException;
import java.util.NoSuchElementException;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    private final RefreshTokenStoreService refreshTokenStoreService;

    @PostMapping("/newToken")
    public ResponseEntity<?> newToken(@RequestBody CreateTokenRequest createTokenRequest) {
        Long userId = createTokenRequest.getId();

        try {
            String accessToken = tokenService.makeAccessToken(createTokenRequest.getId());
            String refreshToken = tokenService.makeRefreshToken(createTokenRequest.getId());

            refreshTokenStoreService.saveRefreshToken(userId, refreshToken);

            AccessRefreshTokenResponse response = new AccessRefreshTokenResponse(accessToken, refreshToken);
            return ResponseEntity.ok(response);
        } catch (JwtException e) {
            // JWT 토큰이 유효하지 않거나 파싱 중에 문제가 발생한 경우
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (IllegalArgumentException e) {
            // refreshTokenRequest 가 올바르지 않은 경우
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoSuchElementException e) {
            // 없는 ID일 경우
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        String rawRefreshToken = refreshTokenRequest.getRefreshToken();

        try {
            Long userId = tokenService.parseUserIdFromToken(rawRefreshToken);
            refreshTokenStoreService.validateToken(userId, rawRefreshToken);

            String newAccessToken = tokenService.makeAccessToken(userId);
            String newRefreshToken = tokenService.makeRefreshToken(userId);

            refreshTokenStoreService.saveRefreshToken(userId, newRefreshToken);

            AccessRefreshTokenResponse response = new AccessRefreshTokenResponse(newAccessToken, newRefreshToken);
            return ResponseEntity.ok(response);

        } catch (JwtException e) {
            // JWT 토큰이 유효하지 않거나 파싱 중에 문제가 발생한 경우
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (IllegalArgumentException e) {
            // refreshTokenRequest 가 올바르지 않은 경우
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    @Builder
    static class AccessRefreshTokenResponse {
        private String accessToken;
        private String refreshToken;
    }
}
