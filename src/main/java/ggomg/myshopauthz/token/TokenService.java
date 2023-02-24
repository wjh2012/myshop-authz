package ggomg.myshopauthz.token;

import static ggomg.myshopauthz.token.TokenMaker.createAccessToken;
import static ggomg.myshopauthz.token.TokenMaker.createRefreshToken;

import ggomg.myshopauthz.userAuthority.User;
import ggomg.myshopauthz.userAuthority.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

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
