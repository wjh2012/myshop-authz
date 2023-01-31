package ggomg.myshopauthz.controller.tokenProvider;

import ggomg.myshopauthz.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenProvideController {
    private final TokenService tokenService;

    @GetMapping("/token")
    public String authorize() {
        return tokenService.create();
    }
}
