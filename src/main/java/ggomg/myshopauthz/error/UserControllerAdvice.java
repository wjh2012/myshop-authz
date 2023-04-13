package ggomg.myshopauthz.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("ggomg.myshopauthz.token.userAuthority")
public class UserControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> argExceptionHandle() {
        return ResponseEntity.badRequest().body("IllegalArgumentException");
    }
}
