package com.adjt.chefmanagerapi.core.exceptions;

public class EmailInvalidoException extends BaseException {

    public static final String MSG_FORMATO_DE_EMAIL_INVALIDO_EXEMPLO_CORRETO = "Formato de email inválido. Exemplo correto: usuario@dominio.com.br";

    public EmailInvalidoException() {
        super(MSG_FORMATO_DE_EMAIL_INVALIDO_EXEMPLO_CORRETO);
    }
}
