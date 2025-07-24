package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;
import lombok.Getter;

@Getter
public class EmailTakenException extends RuntimeException {
    private final String errorCode;

    public EmailTakenException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_TAKEN;
    }

}
