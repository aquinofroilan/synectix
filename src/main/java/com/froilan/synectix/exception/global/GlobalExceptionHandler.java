package com.froilan.synectix.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.froilan.synectix.exception.ApiError;
import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.exception.authentication.WrongPasswordException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ApiError> handleWrongPassword(WrongPasswordException ex) {
        ApiError error = new ApiError("WRONG_PASSWORD", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        ApiError error = new ApiError("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex) {
        ApiError error = new ApiError("INTERNAL_ERROR", "Something went wrong.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
