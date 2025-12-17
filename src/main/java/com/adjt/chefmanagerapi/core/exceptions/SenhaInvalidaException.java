package com.adjt.chefmanagerapi.core.exceptions;

public class SenhaInvalidaException extends BaseException {
    public SenhaInvalidaException() {
        super("Senha inválida: mínimo 6 caracteres.");
    }
}
