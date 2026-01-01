package com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha;

import java.util.UUID;

public record AlterarSenhaInput(
        UUID usuarioId,
        String senhaAtual,
        String novaSenha
) {

}
