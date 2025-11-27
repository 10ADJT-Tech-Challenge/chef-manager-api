package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.UsuarioOutput;

import java.util.UUID;

public interface BuscarUsuarioPorId {
    UsuarioOutput executar(UUID id);
}
