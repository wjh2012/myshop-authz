package ggomg.myshopauthz.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    INACTIVE_USER(HttpStatus.FORBIDDEN, -400,"User is inactive"),
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
