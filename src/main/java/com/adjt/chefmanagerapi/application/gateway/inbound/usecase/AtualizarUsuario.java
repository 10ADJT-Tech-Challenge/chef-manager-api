package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.UsuarioOutput;

import java.util.UUID;

public interface AtualizarUsuario {
    UsuarioOutput executar(UUID id, AtualizarUsuarioInput input);
}
