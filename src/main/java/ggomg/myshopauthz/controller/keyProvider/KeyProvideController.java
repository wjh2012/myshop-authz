package ggomg.myshopauthz.controller.keyProvider;

import static ggomg.myshopauthz.key.KeyMaker.keyPair;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KeyProvideController {

    @GetMapping("/pulickey")
    public String distributeKey(){
        return Arrays.toString(keyPair.getPublic().getEncoded());
    }
}
