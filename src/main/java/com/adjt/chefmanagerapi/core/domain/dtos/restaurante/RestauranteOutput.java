package com.adjt.chefmanagerapi.core.domain.dtos.restaurante;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RestauranteOutput(
    UUID id,
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento,
    UsuarioOutput responsavel,
    OffsetDateTime dataUltimaAlteracao
) {

}