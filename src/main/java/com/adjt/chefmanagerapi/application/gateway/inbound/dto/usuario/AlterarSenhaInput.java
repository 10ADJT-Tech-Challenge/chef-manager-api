package com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario;

import java.util.UUID;

public record AlterarSenhaInput(
        UUID usuarioId,
        String senhaAtual,
        String novaSenha
) {

}
