package com.adjt.chefmanagerapi.core.adapters.interfaces;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;

public interface BuscarUsuarioPorNome {
    UsuarioOutput executar(String nome);
}
