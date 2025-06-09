package com.adnetwork.common.excption;

import com.adnetwork.core.variable.ErrorCode;

public abstract class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }

    public abstract ErrorCode getErrorCode();
}
