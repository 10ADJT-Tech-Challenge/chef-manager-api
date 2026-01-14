package com.adjt.chefmanagerapi.core.gateways.interfaces;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayDto;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepositoryGateway {
    RestauranteGatewayDto salvar(RestauranteGatewayDto restaurante);

    Optional<RestauranteGatewayDto> buscarPorNome(String nome);

    Optional<RestauranteGatewayDto> buscarPorId(UUID id);

    boolean existePorId(UUID id);

    void deletarPorId(UUID id);
}
