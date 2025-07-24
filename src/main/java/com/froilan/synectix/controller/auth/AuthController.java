package com.froilan.synectix.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@Valid @RequestBody SignInRequest request) {
        logger.info("Sign in request for user: {}", request.getUser());
        this.authenticationService.SignInUser(request.getUser(), request.getPassword());
        return ResponseEntity.ok("Sign up successful");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @Valid @RequestBody NewClientSignUpRequest request) {
        logger.info("Sign up request for user: {}", request.getEmail());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }
        authenticationService.SignUpUser(request);
        return ResponseEntity.ok("Sign up successful");
    }

}
