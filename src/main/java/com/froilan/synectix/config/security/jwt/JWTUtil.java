package com.froilan.synectix.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JWTUtil {
    private final Algorithm algorithm;

    public JWTUtil(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String generateToken(String username, String uuidString, String companyUuid, List<String> permissions)
            throws IllegalArgumentException, JWTCreationException {
        try {
            return JWT.create()
                    .withSubject(uuidString)
                    .withClaim("username", username)
                    .withClaim("companyUuid", companyUuid)
                    .withArrayClaim("permissions", permissions.toArray(new String[0]))
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid argument");
        } catch (JWTCreationException e) {
            throw new RuntimeException("You need to enable Algorithm.HMAC256");
        }
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    public String getUsernameFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("username").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    public String getUuidFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Failed to decode token", e);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    public String getCompanyUuidFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("companyUuid").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    public boolean isValid(String token) {
        try {
            validateTokenAndRetrieveSubject(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String refreshToken(String username, String uuidString, String companyUuid) {
        return JWT.create()
                .withSubject(uuidString)
                .withClaim("username", username)
                .withClaim("companyUuid", companyUuid)
                .withIssuedAt(new Date())
                .withIssuer("auth0")
                .sign(algorithm);
    }
}
