package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WrongPasswordException extends RuntimeException {
    private final String errorCode;

    public WrongPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.WRONG_PASSWORD;
    }

}
