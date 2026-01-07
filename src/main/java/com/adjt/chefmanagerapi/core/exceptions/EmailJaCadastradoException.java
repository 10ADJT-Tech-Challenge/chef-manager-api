package com.adjt.chefmanagerapi.core.exceptions;

public class EmailJaCadastradoException extends BaseException {
    public EmailJaCadastradoException(String email) {
        super("E-mail já cadastrado: " + email);
    }
}
