package com.adjt.chefmanagerapi.infrastructure.security;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.TokenGeneratorGateway;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenGenerator implements TokenGeneratorGateway {

    private final RSAPrivateKey privateKey;

    public JwtTokenGenerator(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String gerar(Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getId().toString())
                .withClaim("email", usuario.getEmail().email())
                .withClaim("roles", usuario.getTipo().getNome())
                .withIssuedAt(new Date())
                .withExpiresAt(
                        Date.from(Instant.now().plus(1, ChronoUnit.HOURS))
                )
                .sign(Algorithm.RSA256(null, privateKey));
    }
}

