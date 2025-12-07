package com.adjt.chefmanagerapi.core.adapters.interfaces.usuario;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.AlterarSenhaInput;

public interface AlterarSenha {
    void executar(AlterarSenhaInput dto);
}
