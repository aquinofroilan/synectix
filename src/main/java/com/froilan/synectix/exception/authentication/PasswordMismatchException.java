package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {
    private final String errorCode;

    public PasswordMismatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.PASSWORD_MISMATCH;
    }

}
