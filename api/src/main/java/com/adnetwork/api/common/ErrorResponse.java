package com.adnetwork.api.common;

import com.adnetwork.common.excption.BaseException;
import com.adnetwork.core.variable.ErrorCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ErrorResponse {

    private final int code;
    private final String message;
    private final LocalDateTime timestamp;

    public static ErrorResponse from(ErrorCode errorCode) {
        return from(errorCode, errorCode.getMessage());
    }

    public static ErrorResponse from(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse from(BaseException e) {
        return ErrorResponse.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
