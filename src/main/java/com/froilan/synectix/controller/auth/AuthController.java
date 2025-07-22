package com.froilan.synectix.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.froilan.synectix.service.auth.AuthenticationService;
import com.froilan.synectix.util.RequestLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authService) {
        this.authenticationService = authService;
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String username, @RequestParam String password) {
        logger.info("Sign in request for user: {}", username);
        this.authenticationService.SignInUser(username, password);
        return "Sign in successful";
    }

}
