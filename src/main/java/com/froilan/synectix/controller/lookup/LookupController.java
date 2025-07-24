package com.froilan.synectix.controller.lookup;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.froilan.synectix.util.RequestLogger;
import com.froilan.synectix.util.security.GetClientIP;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/lookup")
public class LookupController {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/countries")
    public String getMethodName(HttpServletRequest request) {
        logger.info(LocalDateTime.now().toString(),
                " - Sign in request for user: {}" + GetClientIP.extractClientIp(request));
        return new String();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/organization-types")
    public String getOrganizationTypes(HttpServletRequest request) {
        logger.info(LocalDateTime.now().toString(),
                " - Sign in request for user: {}" + GetClientIP.extractClientIp(request));
        return new String();
    }

}
