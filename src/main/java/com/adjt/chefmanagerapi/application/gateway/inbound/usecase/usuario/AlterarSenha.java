package com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.AlterarSenhaInput;

public interface AlterarSenha {
    void executar(AlterarSenhaInput dto);
}
