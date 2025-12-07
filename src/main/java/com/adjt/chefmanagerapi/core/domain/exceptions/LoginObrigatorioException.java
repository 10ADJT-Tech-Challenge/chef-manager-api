package com.adjt.chefmanagerapi.core.domain.exceptions;

public class LoginObrigatorioException extends DomainException {
    public LoginObrigatorioException() {
        super("Login é obrigatório.");
    }
}
