package com.adjt.chefmanagerapi.application.exception;

public class LoginJaCadastradoException extends DomainException {
    public LoginJaCadastradoException(String login) {
        super("Login já cadastrado: " + login);
    }
}
