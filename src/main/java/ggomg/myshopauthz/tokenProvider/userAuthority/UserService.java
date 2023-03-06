package ggomg.myshopauthz.tokenProvider.userAuthority;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Can't delete. User not found");
        }
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, Role role) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Can't update. User not found");
        }
        userRepository.save(User.of(id, role));
    }

    public void createUser(Long id, Role role) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("Can't create. User ID already exists");
        }
        userRepository.save(User.of(id, role));
    }
}
