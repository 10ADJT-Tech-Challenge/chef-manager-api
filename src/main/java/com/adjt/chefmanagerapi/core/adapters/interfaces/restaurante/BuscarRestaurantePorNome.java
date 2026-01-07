package com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;

import java.util.List;

public interface BuscarRestaurantePorNome {
    List<RestauranteOutput> executar(String nome);
}
