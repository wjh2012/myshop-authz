package ggomg.myshopauthz.response;

import lombok.Builder;

@Builder
public class CustomResponseBody<T> {
    private int code;
    private String message;
    private T data;
}
