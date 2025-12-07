package com.adjt.chefmanagerapi.core.domain.dtos.usuario;

public record AtualizarUsuarioInput(
        String nome,
        String email,
        String login,
        String tipo,
        EnderecoInput endereco
) {}