package com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;

public interface CadastrarUsuario {
    UsuarioOutput executar(CadastrarUsuarioInput input);
}
