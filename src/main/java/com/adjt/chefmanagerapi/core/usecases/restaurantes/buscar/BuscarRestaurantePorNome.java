package com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar;

import com.adjt.chefmanagerapi.core.usecases.common.UseCase;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;

import java.util.List;

public interface BuscarRestaurantePorNome extends UseCase<String, List<RestauranteOutput>>  {

}
