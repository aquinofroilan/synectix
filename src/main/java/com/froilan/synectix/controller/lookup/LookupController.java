package com.froilan.synectix.controller.lookup;

import java.time.LocalDateTime;
import java.util.List;

import com.froilan.synectix.model.lookup.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.froilan.synectix.model.lookup.Country;
import com.froilan.synectix.model.lookup.OrganizationType;
import com.froilan.synectix.service.lookup.LookupService;
import com.froilan.synectix.util.security.GetClientIP;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/lookup")
public class LookupController {
    private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
    private final LookupService lookupService;

    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/countries")
    public List<Country> getCountries(HttpServletRequest request) {
        logger.info(LocalDateTime.now().toString(),
                " - Lookup request from IP: {}" + GetClientIP.extractClientIp(request));
        return ResponseEntity.ok(lookupService.getAllCountries()).getBody();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/organization-types")
    public List<OrganizationType> getOrganizationTypes(HttpServletRequest request) {
        logger.info(LocalDateTime.now().toString(),
                " - Lookup request from IP: {}" + GetClientIP.extractClientIp(request));
        return ResponseEntity.ok(lookupService.getAllOrganizationTypes()).getBody();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/roles")
    public List<Role> getRoles(HttpServletRequest request) {
        logger.info(LocalDateTime.now().toString(),
                " - Lookup request for roles from IP: {}" + GetClientIP.extractClientIp(request));
        return lookupService.getAllRoles();
    }

}
