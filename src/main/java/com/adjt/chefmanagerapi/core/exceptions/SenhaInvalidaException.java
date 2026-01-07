package com.adjt.chefmanagerapi.core.exceptions;

public class SenhaInvalidaException extends BaseException {

    public static final String MSG_SENHA_INVALIDA_MINIMO_CARACTERES = "Senha inválida: mínimo 6 caracteres.";

    public SenhaInvalidaException() {
        super(MSG_SENHA_INVALIDA_MINIMO_CARACTERES);
    }
}
