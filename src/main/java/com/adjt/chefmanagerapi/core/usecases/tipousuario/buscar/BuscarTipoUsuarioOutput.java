package com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar;

import java.util.UUID;

public record BuscarTipoUsuarioOutput(UUID id, String nome, String  categoriaUsuario) {
}
