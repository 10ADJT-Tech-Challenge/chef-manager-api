package com.adjt.chefmanagerapi.core.exceptions;

public class TipoUsuarioJaCadastradoException extends BaseException {
    public TipoUsuarioJaCadastradoException(String nome) {
        super("Tipo de usuário já cadastrado: " + nome);
    }
}
