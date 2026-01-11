package com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar;

import java.util.UUID;

public record CadastrarRestauranteInput(
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento,
    UUID responsavel
) {
}