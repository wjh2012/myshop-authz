package ggomg.myshopauthz.token.refreshToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private boolean findRefreshToken(Long id) {
        return refreshTokenRepository.existsById(id);
    }

    public void saveRefreshToken(Long id, String token) {
        refreshTokenRepository.save(RefreshToken.of(id, token));
    }
}
