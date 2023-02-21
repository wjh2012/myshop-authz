package ggomg.myshopauthz.userAuthority;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(Long id, String role) {
        validateDuplicated(id);
        userRepository.save(User.of(id, role));
    }

    private void validateDuplicated(Long id) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
    }
}