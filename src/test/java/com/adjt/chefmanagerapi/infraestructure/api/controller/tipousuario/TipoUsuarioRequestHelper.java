package com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario;

import com.adjt.chefmanagerapi.model.TipoUsuarioRequest;

public abstract class TipoUsuarioRequestHelper {
    static final String UUID_TIPO_USUARIO_ADMIN = "cec64cf0-6dc9-4b4e-b0b8-405870ae1b43";
    static final String UUID_TIPO_USUARIO_DONO_RESTAURANTE = "f6f2a623-b22b-4494-bb35-cff956c86e5c";
    static final String UUID_TIPO_USUARIO_CLIENTE = "e98915ad-2a2f-4463-ac5f-38258d3ffa5d";

    public static TipoUsuarioRequest getTipoUsuarioClienteRequest() {
        return new TipoUsuarioRequest()
                .nome("UsuarioTeste")
                .categoriaUsuario(TipoUsuarioRequest.CategoriaUsuarioEnum.CLIENTE);
    }

    public static TipoUsuarioRequest getAtualizarTipoUsuarioRequest() {
        return new TipoUsuarioRequest()
                .nome("UsuarioAtualizado")
                .categoriaUsuario(TipoUsuarioRequest.CategoriaUsuarioEnum.CLIENTE);
    }
}
