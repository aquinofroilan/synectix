package com.froilan.synectix.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.exception.authentication.WrongPasswordException;
import com.froilan.synectix.model.dto.request.authentication.NewClientSignUpRequest;
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
        userRepository.findByEmail(username).ifPresentOrElse(
                user -> {
                    if (passwordEncoder.matches(password, user.getHashedPassword())) {
                        System.out.println("User " + username + " signed in successfully.");
                    } else {
                        throw new WrongPasswordException("The password you entered is incorrect.");
                    }
                },
                () -> {
                    throw new UserNotFoundException("User with that email or username does not exist.");
                });
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
