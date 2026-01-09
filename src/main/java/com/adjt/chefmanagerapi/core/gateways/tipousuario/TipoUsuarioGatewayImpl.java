package com.adjt.chefmanagerapi.core.gateways.tipousuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.TipoUsuarioRepositoryGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {
    private final TipoUsuarioRepositoryGateway repo;

    private final TipoUsuarioGatewayMapper mapper;

    public TipoUsuarioGatewayImpl(TipoUsuarioRepositoryGateway repo, TipoUsuarioGatewayMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        return mapper.toDomain(
                repo.salvar(mapper.toGatewayDTO(tipoUsuario))
        );
    }

    @Override
    public boolean isExisteComNome(String nome) {
        return repo.isExisteComNome(nome);
    }

    @Override
    public Optional<TipoUsuario> buscaPorId(UUID id) {
        return repo.buscaPorId(id)
                .map(mapper::toDomain);

    }

    @Override
    public Iterable<TipoUsuario> buscarTodos() {
        Iterable<TipoUsuarioGatewayDTO> todos = repo.buscarTodos();
        return StreamSupport.stream(todos.spliterator(), false)
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean isExistePorId(UUID id) {
        return repo.isExistePorId(id);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deletarPorId(id);
    }
}
