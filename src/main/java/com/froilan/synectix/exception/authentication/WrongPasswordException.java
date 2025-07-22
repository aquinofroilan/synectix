package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

public class WrongPasswordException extends RuntimeException {
    private final String errorCode;

    public WrongPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.WRONG_PASSWORD;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
