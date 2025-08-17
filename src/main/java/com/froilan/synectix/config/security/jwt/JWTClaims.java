package com.froilan.synectix.config.security.jwt;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JWTClaims {
    public String getCurrentUserId() {
        return getClaimFromCurrentJwt("sub");
    }

    public String getCurrentUserUuid() {
        return getClaimFromCurrentJwt("userUuid");
    }

    public String getCurrentUsername() {
        return getClaimFromCurrentJwt("username");
    }

    public String getCurrentCompanyUuid() {
        return getClaimFromCurrentJwt("companyUuid");
    }

    public List<String> getCurrentUserPermissions() {
        Jwt jwt = getCurrentJwt();
        return jwt.getClaimAsStringList("permissions");
    }

    public String getClaimFromCurrentJwt(String claimName) {
        Jwt jwt = getCurrentJwt();
        return jwt.getClaimAsString(claimName);
    }

    public Jwt getCurrentJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtToken) {
            return jwtToken.getToken();
        }

        throw new IllegalStateException("No JWT token found in security context");
    }

    public boolean hasPermission(String permission) {
        List<String> permissions = getCurrentUserPermissions();
        return permissions != null && permissions.contains(permission);
    }
}
