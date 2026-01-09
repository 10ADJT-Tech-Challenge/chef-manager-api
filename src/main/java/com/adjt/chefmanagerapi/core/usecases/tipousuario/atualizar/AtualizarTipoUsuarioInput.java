package com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar;

import java.util.UUID;

public record AtualizarTipoUsuarioInput(UUID id, String nome, String categoriaUsuario) {
}
