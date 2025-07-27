package com.froilan.synectix.service.company.management;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.exception.authentication.ConflictException;
import com.froilan.synectix.exception.authentication.PasswordMismatchException;
import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.company.NewCompanyUserRequest;
import com.froilan.synectix.repository.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagementService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {ConflictException.class, PasswordMismatchException.class})
    public void createUser(NewCompanyUserRequest newUserRequest) throws PasswordMismatchException, ConflictException {
        if (!newUserRequest.getPassword().equals(newUserRequest.getConfirmPassword()))
            throw new PasswordMismatchException("Passwords do not match");
        if (userRepository.existsByEmail(newUserRequest.getEmail()))
            throw new ConflictException("email", "Email already in use.");

        if (userRepository.existsByPhoneNumber(newUserRequest.getPhoneNumber()))
            throw new ConflictException("phone number", "Phone number already in use.");

        if (userRepository.existsByUsername(newUserRequest.getUsername()))
            throw new ConflictException("username", "Username already in use.");
        User user = new User();
        user.setUsername(newUserRequest.getUsername());
        user.setEmail(newUserRequest.getEmail());
        user.setPhoneNumber(newUserRequest.getPhoneNumber());
        user.setHashedPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        user.setActive(true);
        user.setDeleted(false);
        userRepository.save(user);
    }

    public void deleteUserByUUID(String userUUID) throws UserNotFoundException {
        userRepository.findById(UUID.fromString(userUUID)).ifPresentOrElse(user -> {
            user.setDeleted(true);
            userRepository.save(user);
        }, () -> {
            throw new UserNotFoundException("User not found with UUID: " + userUUID);
        });
    }

    public void deactivateUserByUUID(String userUUID) throws UserNotFoundException {
        userRepository.findById(UUID.fromString(userUUID)).ifPresentOrElse(user -> {
            user.setActive(false);
            userRepository.save(user);
        }, () -> {
            throw new UserNotFoundException("User not found with UUID: " + userUUID);
        });
    }

    public List<User> searchUser(String searchTerm) throws UserNotFoundException {
        List<User> user = userRepository.searchByTerm(searchTerm);
        if (user.isEmpty())
            throw new UserNotFoundException("No users found matching the search term: " + searchTerm);
        return user;
    }

    public User getUserByUUID(String userUUID) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(userUUID))
            .orElseThrow(() -> new UserNotFoundException("User not found with UUID: " + userUUID));
    }

    public  Optional<User> getAllCompanyUsers() throws UserNotFoundException {
        Optional<User> users = userRepository.findByCompany_Uuid(UUID.randomUUID());
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found in the company.");
        }
        return users;
    }
}
