package com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
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
