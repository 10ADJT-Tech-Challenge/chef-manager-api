package com.adjt.chefmanagerapi.core.adapters.interfaces.usuario;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;

public interface CadastrarUsuario {
    UsuarioOutput executar(CadastrarUsuarioInput input);
}
