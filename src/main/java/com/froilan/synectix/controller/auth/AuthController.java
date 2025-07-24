package com.froilan.synectix.controller.auth;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.froilan.synectix.exception.authentication.PasswordMismatchException;
import com.froilan.synectix.model.dto.request.authentication.NewClientSignUpRequest;
import com.froilan.synectix.model.dto.request.authentication.SignInRequest;
import com.froilan.synectix.service.auth.AuthenticationService;
import com.froilan.synectix.util.RequestLogger;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authService) {
        this.authenticationService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@Valid @RequestBody SignInRequest request) {
        logger.info(LocalDateTime.now().toString(), " - Sign in request for user: {}", request.getUser());
        Map<String, String> token = this.authenticationService.SignInUser(request.getUser(), request.getPassword());
        return ResponseEntity.ok(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @Valid @RequestBody NewClientSignUpRequest request) {
        logger.info(LocalDateTime.now().toString(), " - Sign up request for user: {}", request.getEmail());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }
        authenticationService.SignUpUser(request);
        return ResponseEntity.ok("Sign up successful");
    }

}
