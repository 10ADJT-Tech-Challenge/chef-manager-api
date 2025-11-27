package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import java.util.UUID;

public interface DeletarUsuarioPorId {
    void executar(UUID id);
}