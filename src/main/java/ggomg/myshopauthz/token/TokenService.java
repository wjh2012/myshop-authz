package ggomg.myshopauthz.token;

import ggomg.myshopauthz.authority.User;
import ggomg.myshopauthz.authority.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenMaker tokenMaker;
    private final UserRepository userRepository;

    public String provideToken(Long id) {
        Optional<User> OptionalUser = userRepository.findById(id);

        if (OptionalUser.isPresent()) {
            User user = OptionalUser.get();
            return tokenMaker.createAccessToken(user.getId(), user.getRole());
        } else {
            throw new NoSuchElementException();
        }
    }

}
