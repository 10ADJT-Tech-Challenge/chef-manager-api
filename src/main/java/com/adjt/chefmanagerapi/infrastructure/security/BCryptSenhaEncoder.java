package com.adjt.chefmanagerapi.infrastructure.security;

import com.adjt.chefmanagerapi.core.adapters.gateways.SenhaEncoderGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptSenhaEncoder implements SenhaEncoderGateway {

    private final BCryptPasswordEncoder encoder;

    public BCryptSenhaEncoder() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean verifica(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
