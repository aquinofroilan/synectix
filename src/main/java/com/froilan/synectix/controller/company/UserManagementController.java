package com.froilan.synectix.controller.company;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.company.NewCompanyUserRequest;
import com.froilan.synectix.service.company.management.UserManagementService;

@RestController
@RequestMapping("/api/company/user-management")
public class UserManagementController {
    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);
    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@NotNull @Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User creation request for: {}", LocalDateTime.now(), request.getUsername());
        userManagementService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody NewCompanyUserRequest request) {
        logger.info("{} - User update request for: {}", LocalDateTime.now(), request.getUsername());
        // TODO: Logic to update a user
        // This would typically involve calling a service method to handle the update logic
        return ResponseEntity.ok("User updated successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("uuid") String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            logger.error("{} - Invalid UUID provided for deletion", LocalDateTime.now());
            return ResponseEntity.badRequest().build();
        }
        logger.info("{} - Deleting user with UUID: {}", LocalDateTime.now(), uuid);
        userManagementService.deleteUserByUUID(uuid);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/list")
    public ResponseEntity<Optional<User>> listUsers() {
        logger.info("{} - User listing request", LocalDateTime.now());
        Optional<User> users = userManagementService.getAllCompanyUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("searchTerm") String searchTerm) {
        logger.info("{} - User search request for term: '{}'", LocalDateTime.now(), searchTerm);
        List<User> result = userManagementService.searchUser(searchTerm);
        return ResponseEntity.ok(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/deactivate/{uuid}")
    public ResponseEntity<?> deactivateUser(@PathVariable("uuid") String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            logger.error("{} - Invalid UUID provided for deactivation", LocalDateTime.now());
            return ResponseEntity.badRequest().build();
        }
        logger.info("{} - User deactivation request for: {}", LocalDateTime.now(), uuid);
        userManagementService.deactivateUserByUUID(uuid);
        return ResponseEntity.ok("User deactivated successfully");
    }

}
