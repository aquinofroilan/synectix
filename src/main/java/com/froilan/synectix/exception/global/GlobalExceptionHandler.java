package com.froilan.synectix.exception.global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.froilan.synectix.exception.ApiError;
import com.froilan.synectix.exception.authentication.ConflictException;
import com.froilan.synectix.exception.authentication.PasswordMismatchException;
import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.exception.authentication.WrongPasswordException;
import com.froilan.synectix.exception.validation.NotFoundException;

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

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleEmailTaken(ConflictException ex) {
        ApiError error = new ApiError("CONFLICT_EXCEPTION", ex.getMessage() + ex.getField());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiError> handlePasswordMismatch(PasswordMismatchException ex) {
        ApiError error = new ApiError("PASSWORD_MISMATCH", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ApiError> handleJWTVerification(JWTVerificationException ex) {
        ApiError error = new ApiError("JWT_VERIFICATION_ERROR", "Invalid JWT token.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
        ApiError error = new ApiError("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errorCode", "VALIDATION_ERROR");
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex) {
        ApiError error = new ApiError("INTERNAL_ERROR", "Something went wrong.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
