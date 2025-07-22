package com.froilan.synectix.exception;

import java.time.LocalDateTime;

public class ApiError {
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
