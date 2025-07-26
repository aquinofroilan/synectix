package com.froilan.synectix.controller.company;

import com.froilan.synectix.model.dto.request.company.NewCompanyUserRequest;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/company/user-management")
public class UserManagementController {
    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User creation request for: {}", LocalDateTime.now(), request.getUsername());
        // Logic to create a user
        // This would typically involve calling a service method to handle the creation logic
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User update request for: {}", LocalDateTime.now(), request.getUsername());
        // Logic to update a user
        // This would typically involve calling a service method to handle the update logic
        return ResponseEntity.ok("User updated successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User deletion request for: {}", LocalDateTime.now(), request.getUsername());
        // Logic to delete a user
        // This would typically involve calling a service method to handle the deletion logic
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/list")
    public ResponseEntity<?> listUsers() {
        logger.info("{} - User listing request", LocalDateTime.now());
        // Logic to list users
        // This would typically involve calling a service method to retrieve the list of users
        return ResponseEntity.ok("List of users");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/search")
    public ResponseEntity<?> searchUsers(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User search request for: {}", LocalDateTime.now(), request.getUsername());
        // Logic to search for users
        // This would typically involve calling a service method to handle the search logic
        return ResponseEntity.ok("Search results for user: " + request.getUsername());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivateUser(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User deactivation request for: {}", LocalDateTime.now(), request.getUsername());
        // Logic to deactivate a user
        // This would typically involve calling a service method to handle the deactivation logic
        return ResponseEntity.ok("User deactivated successfully");
    }

}
