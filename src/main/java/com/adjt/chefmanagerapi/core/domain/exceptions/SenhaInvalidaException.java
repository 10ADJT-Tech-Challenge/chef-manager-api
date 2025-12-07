package com.adjt.chefmanagerapi.core.domain.exceptions;

public class SenhaInvalidaException extends DomainException {
    public SenhaInvalidaException() {
        super("Senha inválida: mínimo 6 caracteres.");
    }
}
