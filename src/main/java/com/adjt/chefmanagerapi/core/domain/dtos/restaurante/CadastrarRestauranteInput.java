package com.adjt.chefmanagerapi.core.domain.dtos.restaurante;

import java.util.UUID;

public record CadastrarRestauranteInput(
    UUID id,
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento,
    UUID responsavel
) {
}