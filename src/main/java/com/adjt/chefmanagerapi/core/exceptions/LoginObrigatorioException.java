package com.adjt.chefmanagerapi.core.exceptions;

public class LoginObrigatorioException extends BaseException {
    public LoginObrigatorioException() {
        super("Login é obrigatório.");
    }
}
