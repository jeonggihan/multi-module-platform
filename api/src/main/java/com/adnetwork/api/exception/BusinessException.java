package com.adnetwork.api.exception;

import com.adnetwork.common.excption.BaseException;
import com.adnetwork.core.variable.ErrorCode;

public class BusinessException extends BaseException {

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    this(errorCode, errorCode.getMessage());
  }

  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  @Override
  public ErrorCode getErrorCode() {
    return this.errorCode;
  }
}
