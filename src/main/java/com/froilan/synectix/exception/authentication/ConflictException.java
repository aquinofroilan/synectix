package com.froilan.synectix.exception.authentication;

import com.froilan.synectix.exception.ErrorCode;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    private final String errorCode;
    private final String field;

    public ConflictException(String field, String message) {
        super(message);
        this.errorCode = ErrorCode.CONFLICT_EXCEPTION;
        this.field = field;
    }
}
