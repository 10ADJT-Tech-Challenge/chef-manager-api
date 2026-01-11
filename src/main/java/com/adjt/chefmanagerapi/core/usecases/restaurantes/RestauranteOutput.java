package com.adjt.chefmanagerapi.core.usecases.restaurantes;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RestauranteOutput(
    UUID id,
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento,
    UUID responsavel,
    OffsetDateTime dataUltimaAlteracao
) {

}