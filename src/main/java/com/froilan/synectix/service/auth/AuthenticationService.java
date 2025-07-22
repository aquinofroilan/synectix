package com.froilan.synectix.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.exception.authentication.WrongPasswordException;
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
        userRepository.findByEmail(username).ifPresentOrElse(
                user -> {
                    if (passwordEncoder.matches(password, user.getHashedPassword())) {
                        System.out.println("User " + username + " signed in successfully.");
                    } else {
                        throw new WrongPasswordException("The password you entered is incorrect.");
                    }
                },
                () -> {
                    System.out.println(username);
                    throw new WrongPasswordException("User with that email or username does not exist.");
                });
    }
}
