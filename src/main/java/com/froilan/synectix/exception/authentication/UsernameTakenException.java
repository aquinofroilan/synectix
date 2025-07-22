package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

public class UsernameTakenException extends RuntimeException {
    private final String errorCode;

    public UsernameTakenException(String message) {
        super(message);
        this.errorCode = ErrorCode.USERNAME_TAKEN;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
