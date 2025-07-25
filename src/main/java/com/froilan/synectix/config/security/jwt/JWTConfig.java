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
public RSAPublicKey rsaPublicKey() throws Exception {
        return jwtKeyLoader.loadPublicKey();
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        return jwtKeyLoader.loadPrivateKey();
    }

    @Bean
    Algorithm jwtAlgorithm(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        return Algorithm.RSA256(publicKey, privateKey);
    }

}
