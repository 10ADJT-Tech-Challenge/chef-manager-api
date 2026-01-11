package com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar;

import java.util.UUID;

public record AtualizarRestauranteInput(
    UUID id,
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento
) {}