package com.froilan.synectix.config.security.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class JWTConfig {

    private final JWTKeyLoader jwtKeyLoader;

    public JWTConfig(JWTKeyLoader jwtKeyLoader) {
        this.jwtKeyLoader = jwtKeyLoader;
    }

    @Bean
    RSAPublicKey rsaPublicKey() {
        try {
            return jwtKeyLoader.loadPublicKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA public key", e);
        }
    }

    @Bean
    RSAPrivateKey rsaPrivateKey() {
        try {
            return jwtKeyLoader.loadPrivateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA private key", e);
        }
    }

    @Bean
    Algorithm jwtAlgorithm(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        return Algorithm.RSA256(publicKey, privateKey);
    }

}
