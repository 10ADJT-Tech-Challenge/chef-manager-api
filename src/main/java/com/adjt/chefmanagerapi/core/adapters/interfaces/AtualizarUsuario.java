package com.adjt.chefmanagerapi.core.adapters.interfaces;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;

import java.util.UUID;

public interface AtualizarUsuario {
    UsuarioOutput executar(UUID id, AtualizarUsuarioInput input);
}
