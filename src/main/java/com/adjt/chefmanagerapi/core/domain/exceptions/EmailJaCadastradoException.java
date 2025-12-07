package com.adjt.chefmanagerapi.core.domain.exceptions;

public class EmailJaCadastradoException extends DomainException {
    public EmailJaCadastradoException(String email) {
        super("E-mail já cadastrado: " + email);
    }
}
