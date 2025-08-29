package com.froilan.synectix.controller.auth;

import com.froilan.synectix.service.auth.UserValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/validate")
@RequiredArgsConstructor
public class UserValidationController {
    private final UserValidationService validationService;

    @GetMapping("/email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(validationService.isEmailTaken(email));
    }

    @GetMapping("/username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(validationService.isUsernameTaken(username));
    }

    @GetMapping("/phone")
    public ResponseEntity<Boolean> checkPhone(@RequestParam String phone) {
        return ResponseEntity.ok(validationService.isPhoneTaken(phone));
    }
}
