package com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar;

import java.util.UUID;

public record CadastrarTipoUsuarioOutput(UUID id, String nome, String  categoriaUsuario) {
}
