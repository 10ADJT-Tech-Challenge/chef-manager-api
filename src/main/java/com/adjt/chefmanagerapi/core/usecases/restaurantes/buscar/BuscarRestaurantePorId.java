package com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar;

import com.adjt.chefmanagerapi.core.usecases.common.UseCase;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;

import java.util.UUID;

public interface BuscarRestaurantePorId extends UseCase<UUID, RestauranteOutput> {

}
