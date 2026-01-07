package com.adjt.chefmanagerapi.core.adapters.gateways;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteGateway {
    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorNome(String nome);

    Optional<Restaurante> buscarPorId(UUID id);

    boolean existePorId(UUID id);

    void deletarPorId(UUID id);
}
