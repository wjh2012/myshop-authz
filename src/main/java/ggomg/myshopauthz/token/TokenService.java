package ggomg.myshopauthz.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenMaker tokenMaker;

    public String create() {
        return tokenMaker.createAccessToken();
    }

}
