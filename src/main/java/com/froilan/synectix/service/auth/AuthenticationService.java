package com.froilan.synectix.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void SignInUser(String username, String password) {
        // Logic to sign in user
        // This is a placeholder for actual authentication logic
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password for user " + username + ": " + encodedPassword);
        System.out.println("Signing in user: " + username);
    }
}
