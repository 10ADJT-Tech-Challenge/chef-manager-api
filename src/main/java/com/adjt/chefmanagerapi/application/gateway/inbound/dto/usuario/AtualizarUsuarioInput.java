package com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario;

public record AtualizarUsuarioInput(
        String nome,
        String email,
        String login,
        String tipo,
        EnderecoInput endereco
) {}