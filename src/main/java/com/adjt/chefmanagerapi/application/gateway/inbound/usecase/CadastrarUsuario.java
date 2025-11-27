package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.UsuarioOutput;

public interface CadastrarUsuario {
    UsuarioOutput executar(CadastrarUsuarioInput input);
}
