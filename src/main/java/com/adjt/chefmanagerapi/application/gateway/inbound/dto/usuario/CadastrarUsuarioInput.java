package com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario;

public record CadastrarUsuarioInput(
        String nome,
        String email,
        String login,
        String senha,
        String tipo,
        EnderecoInput endereco
) {
}