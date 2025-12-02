package com.adjt.chefmanagerapi.application.exception;

public class SenhaInvalidaException extends DomainException {
    public SenhaInvalidaException() {
        super("Senha inválida: mínimo 6 caracteres.");
    }
}
