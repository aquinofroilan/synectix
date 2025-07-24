package com.froilan.synectix.service.auth;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.config.security.jwt.JWTUtil;
import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.exception.authentication.WrongPasswordException;
import com.froilan.synectix.model.dto.request.authentication.NewClientSignUpRequest;
import com.froilan.synectix.repository.user.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Map<String, String> SignInUser(String username, String password) {
        return userRepository.findByEmail(username)
                .map(user -> {
                    if (passwordEncoder.matches(password, user.getHashedPassword())) {
                        user.setLastLogin(LocalDateTime.now());
                        String token = jwtUtil.generateToken(user.getUsername(), user.getUuid().toString());
                        return Collections.singletonMap("jwt-token", token);
                    } else {
                        throw new WrongPasswordException("The password you entered is incorrect.");
                    }
                })
                .orElseThrow(() -> new UserNotFoundException("User with that email or username does not exist."));
    }

    public void SignUpUser(NewClientSignUpRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresentOrElse(user -> {
            throw new UserNotFoundException("User with that email or username already exists.");
        }, () -> {
            // Here you would typically create a new user and save it to the repository
            // For example:
            // User newUser = new User();
            // newUser.setEmail(username);
            // newUser.setHashedPassword(passwordEncoder.encode(password));
            // userRepository.save(newUser);
        });
    }
}
