package com.adjt.chefmanagerapi.core.domain.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.DeletarRestaurantePorId;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeletarRestaurantePorIdUseCase implements DeletarRestaurantePorId {
    private final RestauranteGateway restauranteGateway;

    public DeletarRestaurantePorIdUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public void executar(UUID id) {
        if (!restauranteGateway.existePorId(id))
            throw new NoSuchElementException("Nenhum restaurante encontrado com o id: " + id);

        restauranteGateway.deletarPorId(id);
    }
}