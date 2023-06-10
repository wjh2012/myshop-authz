package ggomg.myshopauthz.response;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();
}
