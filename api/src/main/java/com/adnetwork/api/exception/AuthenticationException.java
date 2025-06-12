package com.adnetwork.api.exception;

import com.adnetwork.common.excption.BaseException;
import com.adnetwork.core.variable.ErrorCode;

public class AuthenticationException extends BaseException {

  private final ErrorCode errorCode;


  public AuthenticationException(ErrorCode errorCode) {
    this(errorCode, errorCode.getMessage());
  }

  public AuthenticationException(String message) {
    this(ErrorCode.UNAUTHORIZED, message);
  }

  public AuthenticationException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  @Override
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
