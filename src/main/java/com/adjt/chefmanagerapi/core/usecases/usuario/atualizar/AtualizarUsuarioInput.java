package com.adjt.chefmanagerapi.core.usecases.usuario.atualizar;

import java.util.UUID;

public record AtualizarUsuarioInput(
        UUID id,
        String nome,
        String email,
        String login,
        UUID tipo,
        EnderecoInput endereco
) {
    public record EnderecoInput(
            String rua,
            String numero,
            String cidade,
            String cep,
            String uf
    ) {}
}