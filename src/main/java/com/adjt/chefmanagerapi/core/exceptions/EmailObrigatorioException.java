package com.adjt.chefmanagerapi.core.exceptions;

public class EmailObrigatorioException extends BaseException {
    public EmailObrigatorioException() {
        super("E-mail é obrigatório.");
    }
}
