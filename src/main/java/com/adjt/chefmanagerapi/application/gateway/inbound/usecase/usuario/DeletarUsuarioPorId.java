package com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario;

import java.util.UUID;

public interface DeletarUsuarioPorId {
    void executar(UUID id);
}