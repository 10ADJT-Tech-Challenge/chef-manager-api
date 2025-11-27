package com.adjt.chefmanagerapi.application.exception;

public class LoginObrigatorioException extends DomainException {
    public LoginObrigatorioException() {
        super("Login é obrigatório.");
    }
}
