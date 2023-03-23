package ggomg.myshopauthz.token.refreshToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long userId, String refreshToken) {
        RefreshToken userIdRefreshToken = RefreshToken.of(userId, refreshToken);

        refreshTokenRepository.save(userIdRefreshToken);
    }

    public boolean findRefreshTokenByUserId(Long userId) {
        return refreshTokenRepository.existsById(userId);
    }

}