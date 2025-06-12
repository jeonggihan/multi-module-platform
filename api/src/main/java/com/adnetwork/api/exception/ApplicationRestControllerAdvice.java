package com.adnetwork.api.exception;

import com.adnetwork.api.common.ErrorResponse;
import com.adnetwork.common.excption.BaseException;
import com.adnetwork.core.variable.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationRestControllerAdvice {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(BaseException e) {
    return ResponseEntity
        .status(e.getErrorCode().getCode())
        .body(ErrorResponse.from(e));
  }

  @ExceptionHandler(InternalAuthenticationServiceException.class)
  public ResponseEntity<ErrorResponse> handleException(InternalAuthenticationServiceException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(ErrorResponse.from(ErrorCode.UNAUTHORIZED, e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("Unhandled exception occurred", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR));
  }

}
