package com.adnetwork.api.exception;

import com.adnetwork.common.excption.BaseException;
import com.adnetwork.core.variable.ErrorCode;

public class BadRequestException extends BaseException {

  private final ErrorCode errorCode;

  public BadRequestException() {
    this(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
  }

  public BadRequestException(String message) {
    this(ErrorCode.BAD_REQUEST, message);
  }

  public BadRequestException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  @Override
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
