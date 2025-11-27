package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.UsuarioOutput;

public interface BuscarUsuarioPorNome {
    UsuarioOutput executar(String nome);
}
