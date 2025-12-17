package com.adjt.chefmanagerapi.core.usecases.usuario.buscar;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BuscarUsuarioOutput(
        UUID id,
        String nome,
        String email,
        String login,
        String tipo,
        String token,
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
}