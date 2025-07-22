package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    private final String errorCode;

    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_NOT_FOUND;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
