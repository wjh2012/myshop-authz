package ggomg.myshopauthz.tokenProvider;

import static ggomg.myshopauthz.tokenProvider.TokenMaker.createAccessToken;
import static ggomg.myshopauthz.tokenProvider.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.tokenProvider.userAuthority.User;
import ggomg.myshopauthz.tokenProvider.userAuthority.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenProvideService {

    private final UserRepository userRepository;

    public String provideAccessToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createAccessToken(user.getId(), user.getRole());
    }

    public String provideRefreshToken(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return createRefreshToken(user.getId(), user.getRole());
    }

}
