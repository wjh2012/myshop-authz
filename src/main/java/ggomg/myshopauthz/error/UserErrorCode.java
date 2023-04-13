package ggomg.myshopauthz.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode{

    CANT_CREATE_USER(4011, "User ID already exist"),
    USER_NOT_FOUND(4012, "User not Found"),

    USER_ID_NOT_VALID(4021,"Unable id format"),
    USER_ROLE_NOT_VALID(4022,"User Role not valid")
    ;

    private int code;
    private String message;
}
