package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.mapper.RestaurantePersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RestauranteJpaGateway implements RestauranteGateway {

    private final RestauranteJPARepository repo;
    private final RestaurantePersistenceMapper mapper;

    public RestauranteJpaGateway(RestauranteJPARepository repo, RestaurantePersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity e = mapper.toEntity(restaurante);
        e = repo.save(e);
        return mapper.toDomain(e);
    }

    public Optional<Restaurante> buscarPorNome(String nome) {
        return repo.findByNome(nome).map(mapper::toDomain);
    }

    @Override
    public Optional<Restaurante> buscarPorId(UUID id) {
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
