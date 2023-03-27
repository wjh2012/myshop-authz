package ggomg.myshopauthz.token.refreshToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenStoreService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void validateToken(Long id, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not exist"));

        if (!refreshToken.getRefreshToken().equals(token)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

    }

    public void saveRefreshToken(Long userId, String rawRefreshToken) {
        RefreshToken userIdRefreshToken = RefreshToken.of(userId, rawRefreshToken);

        refreshTokenRepository.save(userIdRefreshToken);
    }
}