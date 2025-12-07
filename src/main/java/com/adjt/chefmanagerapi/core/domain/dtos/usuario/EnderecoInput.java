package com.adjt.chefmanagerapi.core.domain.dtos.usuario;

public record EnderecoInput(
        String rua,
        String numero,
        String cidade,
        String cep,
        String uf
) {
}