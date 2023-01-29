package ggomg.myshopauthz.controller.tokenProvider;

import ggomg.myshopauthz.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenProvideController {
    private TokenService tokenService;

    @PostMapping("/authorize")
    public String authorize(@RequestBody String id) {
        return tokenService.create();
    }


}
