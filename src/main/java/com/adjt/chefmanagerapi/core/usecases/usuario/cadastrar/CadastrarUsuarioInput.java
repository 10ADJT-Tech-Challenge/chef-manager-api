package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

import java.util.UUID;

public record CadastrarUsuarioInput(
        String nome,
        String email,
        String login,
        String senha,
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