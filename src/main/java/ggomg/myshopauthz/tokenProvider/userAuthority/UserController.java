package ggomg.myshopauthz.tokenProvider.userAuthority;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserDTO request) {
        userService.createUser(request.getId(), request.getRole());
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody UserDTO request) {
        userService.deleteUser(request.getId());
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserDTO request) {
        userService.updateUser(request.getId(), request.getRole());
    }
}
