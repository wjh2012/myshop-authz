package ggomg.myshopauthz.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(Long id, String role){
        userRepository.save(User.of(id,role));
    }
}
