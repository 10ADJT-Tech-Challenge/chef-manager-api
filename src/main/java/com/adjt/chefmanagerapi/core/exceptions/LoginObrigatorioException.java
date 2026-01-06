package com.adjt.chefmanagerapi.core.exceptions;

public class LoginObrigatorioException extends BaseException {

    public static final String MSG_LOGIN_OBRIGATORIO = "Login é obrigatório";

    public LoginObrigatorioException() {
        super(MSG_LOGIN_OBRIGATORIO);
    }
}
