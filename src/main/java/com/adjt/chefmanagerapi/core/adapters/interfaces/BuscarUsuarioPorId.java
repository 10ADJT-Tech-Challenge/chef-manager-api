package com.adjt.chefmanagerapi.core.adapters.interfaces;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;

import java.util.UUID;

public interface BuscarUsuarioPorId {
    UsuarioOutput executar(UUID id);
}
