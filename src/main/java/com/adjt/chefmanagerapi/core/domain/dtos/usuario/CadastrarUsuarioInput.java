package com.adjt.chefmanagerapi.core.domain.dtos.usuario;

public record CadastrarUsuarioInput(
        String nome,
        String email,
        String login,
        String senha,
        String tipo,
        EnderecoInput endereco
) {
}