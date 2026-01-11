package com.adjt.chefmanagerapi.core.usecases.restaurantes.deletar;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
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
    public Void executar(UUID id) {
        if (!restauranteGateway.existePorId(id))
            throw new NoSuchElementException("Nenhum restaurante encontrado com o id: " + id);

        restauranteGateway.deletarPorId(id);
        return null;
    }
}