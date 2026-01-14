package com.demo.mcctiendademo.Util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SuccessMessage <T> {
    private Boolean isError = Boolean.FALSE;
    private Integer code;
    private String message;
    private T data;

    public static <T> SuccessMessage<T> success(HttpStatus status, String message, T data) {
        return SuccessMessage.<T>builder()
                .isError(false)
                .code(status.value())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> SuccessMessage<T> error(HttpStatus status, String message) {
        return SuccessMessage.<T>builder()
                .isError(true)
                .code(status.value())
                .message(message)
                .data(null)
                .build();
    }
}
