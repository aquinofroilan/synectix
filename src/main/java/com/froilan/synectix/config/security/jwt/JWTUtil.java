package com.froilan.synectix.config.security.jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTUtil {
    private final Algorithm algorithm;

    public JWTUtil(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String generateToken(String username, String uuidString)
            throws IllegalArgumentException, JWTCreationException {
        try {
            return JWT.create()
                    .withSubject(uuidString)
                    .withClaim("username", username)
                    .withIssuedAt(new Date())
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
                .withSubject("User UUID")
                .withIssuer("auth0")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
