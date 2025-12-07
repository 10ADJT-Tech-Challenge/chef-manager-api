package com.adjt.chefmanagerapi.core.domain.exceptions;

public class LoginJaCadastradoException extends DomainException {
    public LoginJaCadastradoException(String login) {
        super("Login já cadastrado: " + login);
    }
}
