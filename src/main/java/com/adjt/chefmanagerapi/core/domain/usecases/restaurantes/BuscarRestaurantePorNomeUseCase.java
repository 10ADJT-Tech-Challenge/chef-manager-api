package com.adjt.chefmanagerapi.core.domain.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.BuscarRestaurantePorNome;
import com.adjt.chefmanagerapi.core.adapters.mappers.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuscarRestaurantePorNomeUseCase implements BuscarRestaurantePorNome {
    private final RestauranteGateway restauranteGateway;
    private final RestauranteMapper mapper;

    public BuscarRestaurantePorNomeUseCase(RestauranteGateway restauranteGateway, RestauranteMapper mapper) {
        this.restauranteGateway = restauranteGateway;
        this.mapper = mapper;
    }

    @Override
    public List<RestauranteOutput> executar(String nome) {
        Optional<Restaurante> optRestaurante = restauranteGateway.buscarPorNome(nome);
        if (optRestaurante.isEmpty())
            throw new NoSuchElementException("Nenhum restaurante encontrado com esse nome: " + nome);

        return optRestaurante.stream().map(mapper::toOutput).toList();
    }
}
