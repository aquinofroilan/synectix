package com.froilan.synectix.controller.auth;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;

import com.froilan.synectix.config.security.jwt.JWTUtil;
import com.froilan.synectix.model.dto.request.authentication.NewClientSignUpRequest;
import com.froilan.synectix.model.dto.request.authentication.SignInRequest;
import com.froilan.synectix.service.auth.AuthenticationService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationService authenticationService;
    private final JWTUtil jwtUtil;

    public AuthController(AuthenticationService authService, JWTUtil jwtUtil) {
        this.authenticationService = authService;
        this.jwtUtil = jwtUtil;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@Valid @RequestBody SignInRequest request) {
        logger.info("{} - Sign in request for user: {}", LocalDateTime.now(), request.getUser());
        Map<String, String> tokens = this.authenticationService.signInUser(request.getUser(), request.getPassword());
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", tokens.get("refreshToken"))
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/api/auth/refresh")
                .maxAge(Duration.ofDays(30))
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(Map.of("access_token", tokens.get("accessToken")));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue("refresh_token") String refreshToken) {
        logger.info("{} - Refresh token request", LocalDateTime.now());
        if (!jwtUtil.isValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
        String newAccessToken = jwtUtil.refreshToken(jwtUtil.getUsernameFromToken(refreshToken),
                jwtUtil.getUuidFromToken(refreshToken));
        return ResponseEntity.ok(Map.of("access_token", newAccessToken));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(
            @Valid @RequestBody NewClientSignUpRequest request) {
        logger.info("{} - Sign up request for user: {}", LocalDateTime.now(), request.getEmail());
        Map<String, String> tokens = authenticationService.signUpUser(request);
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", tokens.get("refreshToken"))
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/api/auth/refresh")
                .maxAge(Duration.ofDays(30))
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(Map.of("access_token", tokens.get("accessToken")));
    }

}
