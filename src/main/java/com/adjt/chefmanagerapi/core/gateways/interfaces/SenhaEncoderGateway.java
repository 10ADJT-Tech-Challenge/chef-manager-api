package com.adjt.chefmanagerapi.core.gateways.interfaces;

public interface SenhaEncoderGateway {
    String encode(CharSequence rawPassword);
    boolean verifica(CharSequence rawPassword, String encodedPassword);
}
