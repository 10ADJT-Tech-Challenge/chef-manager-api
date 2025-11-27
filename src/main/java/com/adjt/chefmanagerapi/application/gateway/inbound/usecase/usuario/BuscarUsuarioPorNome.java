package com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;

public interface BuscarUsuarioPorNome {
    UsuarioOutput executar(String nome);
}
