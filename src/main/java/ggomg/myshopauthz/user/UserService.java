package ggomg.myshopauthz.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private void checkUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User ID not exists");
        }
    }

    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(Long id, Role role) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("User ID already exists");
        }
        userRepository.save(User.of(id, role));
    }

    public void deleteUser(Long id) {
        checkUserExists(id);
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, Role role) {
        checkUserExists(id);
        userRepository.save(User.of(id, role));
    }

}
