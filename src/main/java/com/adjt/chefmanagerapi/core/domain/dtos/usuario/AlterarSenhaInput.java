package com.adjt.chefmanagerapi.core.domain.dtos.usuario;

import java.util.UUID;

public record AlterarSenhaInput(
        UUID usuarioId,
        String senhaAtual,
        String novaSenha
) {

}
