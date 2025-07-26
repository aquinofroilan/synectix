package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

import lombok.Getter;

@Getter
public class WrongMFACodeException extends RuntimeException {
    private final String errorCode;

    public WrongMFACodeException(String message) {
        super(message);
        this.errorCode = ErrorCode.WRONG_MFA_CODE;
    }
}
