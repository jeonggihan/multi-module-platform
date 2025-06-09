package com.adnetwork.core.variable;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST(400, "잘못된 요청입니다."),
    UNAUTHORIZED(401, "인증이 필요합니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
    BUSINESS_ERROR(6000, "비즈니스 로직 오류입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
