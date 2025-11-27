package com.adjt.chefmanagerapi.application.gateway.inbound.usecase;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.AlterarSenhaInput;

public interface AlterarSenha {
    void executar(AlterarSenhaInput dto);
}
