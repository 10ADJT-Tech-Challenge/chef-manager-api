package com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;

import java.util.UUID;

public interface BuscarUsuarioPorId {
    UsuarioOutput executar(UUID id);
}
