package com.froilan.synectix.exception.validation;

import com.froilan.synectix.exception.ErrorCode;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String errorCode;

    public NotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_FOUND;
    }

}
