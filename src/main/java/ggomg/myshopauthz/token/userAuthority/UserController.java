package ggomg.myshopauthz.token.userAuthority;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserDTO request) {
        userService.saveUser(request.getId(), request.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserDTO request) {
        userService.deleteUser(request.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO request) {
        userService.updateUser(request.getId(), request.getRole());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
