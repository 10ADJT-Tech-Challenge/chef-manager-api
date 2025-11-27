package com.adjt.chefmanagerapi.application.exception;

public class EmailObrigatorioException extends DomainException {
    public EmailObrigatorioException() {
        super("E-mail é obrigatório.");
    }
}
