package com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario;

public record EnderecoInput(
        String rua,
        String numero,
        String cidade,
        String cep,
        String uf
) {
}