package com.adjt.chefmanagerapi.infrastructure.security;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class RsaKeyConfig {

    @Value("${security.jwt.private-key}")
    private Resource privateKeyResource;

    @Value("${security.jwt.public-key}")
    private Resource publicKeyResource;

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        return (RSAPrivateKey) readKey(privateKeyResource);
    }

    @Bean
    public RSAPublicKey rsaPublicKey() throws Exception {
        return (RSAPublicKey) readKey(publicKeyResource);
    }

    private Key readKey(Resource resource) throws Exception {
        String key = new String(resource.getInputStream().readAllBytes())
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        if (resource.getFilename().contains("private")) {
            return kf.generatePrivate(new PKCS8EncodedKeySpec(decoded));
        }
        return kf.generatePublic(new X509EncodedKeySpec(decoded));
    }
}

