package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CadastrarUsuarioOutput(
        UUID id,
        String nome,
        String email,
        String login,
        TipoUsuarioOutput tipo,
        EnderecoOutput endereco,
        OffsetDateTime dataUltimaAlteracao
) {
    public record EnderecoOutput(
            String rua,
            String numero,
            String cidade,
            String cep,
            String uf
    ) {}

    public record TipoUsuarioOutput(
            UUID id,
            String nome,
            String categoriaUsuario
    ) {}
}