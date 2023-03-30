package ggomg.myshopauthz.token.userAuthority;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> argExceptionHandle() {
        return ResponseEntity.badRequest().body("IllegalArgumentException");
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> dtoExceptionHandle() {
        return ResponseEntity.badRequest().body("Json Mapping Error");
    }
}
