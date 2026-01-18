package com.adjt.chefmanagerapi.core.gateways.restaurante;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.gateways.interfaces.RestauranteRepositoryGateway;

import java.util.Optional;
import java.util.UUID;


public class RestauranteGatewayImpl implements RestauranteGateway {
    private final RestauranteRepositoryGateway repo;

    private final RestauranteGatewayMapper mapper;

    public RestauranteGatewayImpl(RestauranteRepositoryGateway repo, RestauranteGatewayMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return mapper.toDomain(
                repo.salvar(mapper.toGatewayDTO(restaurante))
        );
    }

    @Override
    public Optional<Restaurante> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome).map(mapper::toDomain);
    }

    @Override
    public Optional<Restaurante> buscarPorId(UUID id) {
        return repo.buscarPorId(id).map(mapper::toDomain);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existePorId(id);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deletarPorId(id);
    }
}
