package com.adjt.chefmanagerapi.core.exceptions;

import java.util.UUID;

public class TipoUsuarioNaoEncontradoException extends BaseException {

    public static final String MSG_TIPO_DE_USUARIO_NAO_ENCONTRADO = "Tipo de usuário não encontrado para o ID:";

    public TipoUsuarioNaoEncontradoException(UUID id) {
        super(MSG_TIPO_DE_USUARIO_NAO_ENCONTRADO + id);
    }
}
