package com.froilan.synectix.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.repository.user.UserRepository;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void SignInUser(String username, String password) {
        // Logic to sign in user
        // This is a placeholder for actual authentication logic
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password for user " + username + ": " + encodedPassword);
        userRepository.findByEmail(username).ifPresent(user -> {
            if (passwordEncoder.matches(password, user.getHashedPassword())) {
                System.out.println("User " + username + " signed in successfully.");
            } else {
                System.out.println("Invalid password for user " + username);
            }
        });
        System.out.println("Signing in user: " + username);
    }
}
