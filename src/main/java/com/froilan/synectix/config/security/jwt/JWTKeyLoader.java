package com.froilan.synectix.config.security.jwt;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class JWTKeyLoader {

    public RSAPublicKey loadPublicKey() throws Exception {
        PemObject pemObject = readPem("keys/public_key.pem");
        byte[] content = pemObject.getContent();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(content);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    public RSAPrivateKey loadPrivateKey() throws Exception {
        PemObject pemObject = readPem("keys/private_key.pem");
        byte[] content = pemObject.getContent();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(content);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    private PemObject readPem(String path) throws Exception {
        try (PemReader reader = new PemReader(new InputStreamReader(new ClassPathResource(path).getInputStream()))) {
            return reader.readPemObject();
        }
    }
}
