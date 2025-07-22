package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

public class EmailTakenException extends RuntimeException {
    private final String errorCode;

    public EmailTakenException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_TAKEN;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
