package com.adjt.chefmanagerapi.core.adapters.gateways;

public interface SenhaEncoderGateway {
    String encode(CharSequence rawPassword);
    boolean verifica(CharSequence rawPassword, String encodedPassword);
}
