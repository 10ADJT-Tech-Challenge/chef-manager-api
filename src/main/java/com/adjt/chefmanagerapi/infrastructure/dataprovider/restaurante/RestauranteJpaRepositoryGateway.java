package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import com.adjt.chefmanagerapi.core.gateways.interfaces.RestauranteRepositoryGateway;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayDto;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RestauranteJpaRepositoryGateway implements RestauranteRepositoryGateway {

    private final RestauranteJPARepository repo;
    private final RestaurantePersistenceMapper mapper;

    public RestauranteJpaRepositoryGateway(RestauranteJPARepository repo, RestaurantePersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public RestauranteGatewayDto salvar(RestauranteGatewayDto restaurante) {
        RestauranteEntity e = mapper.toEntity(restaurante);
        e = repo.save(e);
        return mapper.toDomain(e);
    }

    public Optional<RestauranteGatewayDto> buscarPorNome(String nome) {
        return repo.findByNome(nome).map(mapper::toDomain);
    }

    @Override
    public Optional<RestauranteGatewayDto> buscarPorId(UUID id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existsById(id);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deleteById(id);
    }
}
