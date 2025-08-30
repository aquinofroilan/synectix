package com.froilan.synectix.controller.auth;

import com.froilan.synectix.service.auth.UserValidationService;
import com.froilan.synectix.util.security.HashUtil;

import java.util.Map;

import org.slf4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/validate")
public class UserValidationController {
    private final UserValidationService validationService;
    private static final Logger logger = LoggerFactory.getLogger(UserValidationController.class);

    public UserValidationController(UserValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam(name = "email") String email) {
        boolean taken = validationService.isEmailTaken(email);
        logger.info("Email validation request. endpoint=/api/auth/validate/email, emailHash={}, result={}",
                HashUtil.hashEmail(email),
                taken);
        return ResponseEntity.ok(Map.of("email_taken", taken));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam(name = "username") String username) {
        boolean taken = validationService.isUsernameTaken(username);
        logger.info("Username validation request. endpoint=/api/auth/validate/username, usernameHash={}, result={}",
                HashUtil.hashEmail(username),
                taken);
        return ResponseEntity.ok(Map.of("username_taken", taken));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/phone")
    public ResponseEntity<Map<String, Boolean>> checkPhone(@RequestParam(name = "phone") String phone) {
        boolean taken = validationService.isPhoneTaken(phone);
        logger.info("Phone validation request. endpoint=/api/auth/validate/phone, phoneHash={}, result={}",
                HashUtil.hashEmail(phone),
                taken);
        return ResponseEntity.ok(Map.of("phone_taken", taken));
    }
}
