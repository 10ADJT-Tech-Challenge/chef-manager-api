package com.adjt.chefmanagerapi.core.exceptions;

public class EmailObrigatorioException extends BaseException {

    public static final String MSG_EMAIL_E_OBRIGATORIO = "E-mail é obrigatório.";

    public EmailObrigatorioException() {
        super(MSG_EMAIL_E_OBRIGATORIO);
    }
}
