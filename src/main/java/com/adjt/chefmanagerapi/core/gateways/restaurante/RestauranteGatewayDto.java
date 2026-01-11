package com.adjt.chefmanagerapi.core.gateways.restaurante;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RestauranteGatewayDto(UUID id,
                                    String nome,
                                    String endereco,
                                    String tipoCozinha,
                                    String horarioFuncionamento,
                                    UUID responsavelId,
                                    UsuarioGatewayDTO responsavel,
                                    OffsetDateTime dataUltimaAlteracao) {
}