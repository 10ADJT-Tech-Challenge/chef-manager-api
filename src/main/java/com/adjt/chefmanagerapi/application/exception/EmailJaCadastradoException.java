package com.adjt.chefmanagerapi.application.exception;

public class EmailJaCadastradoException extends DomainException {
    public EmailJaCadastradoException(String email) {
        super("E-mail já cadastrado: " + email);
    }
}
