package com.froilan.synectix.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
