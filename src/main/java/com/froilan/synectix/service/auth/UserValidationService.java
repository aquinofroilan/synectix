package com.froilan.synectix.service.auth;

import com.froilan.synectix.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {
    private final UserRepository userRepository;
    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Boolean isEmailTaken(String email) {
        System.out.println(email);
        System.out.println(userRepository.existsByEmail(email));
        return userRepository.existsByEmail(email);
    }

    public Boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean isPhoneTaken(String phone) {
        return userRepository.existsByPhoneNumber(phone);
    }

}
