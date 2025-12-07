package com.adjt.chefmanagerapi.core.domain.exceptions;

public class EmailObrigatorioException extends DomainException {
    public EmailObrigatorioException() {
        super("E-mail é obrigatório.");
    }
}
