package ggomg.myshopauthz.token;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenProvideController {
    private final TokenService tokenService;

    @GetMapping("/token")
    public String authorize(@RequestBody TokenRequest request) {
        return tokenService.provideToken(request.getId());
    }
}
