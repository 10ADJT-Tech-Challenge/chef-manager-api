package com.adjt.chefmanagerapi.core.domain.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.BuscarRestaurantePorId;
import com.adjt.chefmanagerapi.core.adapters.mappers.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarRestaurantePorIdUseCase implements BuscarRestaurantePorId {

    private final RestauranteGateway restauranteGateway;
    private final RestauranteMapper mapper;

    public BuscarRestaurantePorIdUseCase(RestauranteGateway restauranteGateway, RestauranteMapper mapper) {
        this.restauranteGateway = restauranteGateway;
        this.mapper = mapper;
    }

    @Override
    public RestauranteOutput executar(UUID id) {
        Optional<Restaurante> optRestaurante = restauranteGateway.buscarPorId(id);
        if (optRestaurante.isEmpty())
            throw new NoSuchElementException("Nenhum restaurante encontrado com o id: " + id);

        return mapper.toOutput(optRestaurante.get());
    }
}
