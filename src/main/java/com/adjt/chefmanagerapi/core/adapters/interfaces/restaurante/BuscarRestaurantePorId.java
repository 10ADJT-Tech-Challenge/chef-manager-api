package com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;

import java.util.UUID;

public interface BuscarRestaurantePorId {
    RestauranteOutput executar(UUID id);
}
