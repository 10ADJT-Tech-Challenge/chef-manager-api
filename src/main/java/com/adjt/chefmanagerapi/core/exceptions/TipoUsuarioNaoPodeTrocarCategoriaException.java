package com.adjt.chefmanagerapi.core.exceptions;

public class TipoUsuarioNaoPodeTrocarCategoriaException extends BaseException {

    public static final String MSG_TIPO_DE_USUARIO_NAO_PODE_TROCAR_DE_CATEGORIA = "Tipo de usuário não pode trocar de categoria.";

    public TipoUsuarioNaoPodeTrocarCategoriaException() {
        super(MSG_TIPO_DE_USUARIO_NAO_PODE_TROCAR_DE_CATEGORIA);
    }
}
