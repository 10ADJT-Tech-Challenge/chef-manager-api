package com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario;

import com.adjt.chefmanagerapi.core.gateways.interfaces.UsuarioRepositoryGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioJpaRepositoryGateway implements UsuarioRepositoryGateway {

    private final UsuarioJPARepository repo;
    private final UsuarioPersistenceMapper mapper;

    public UsuarioJpaRepositoryGateway(UsuarioJPARepository repo, UsuarioPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public UsuarioGatewayDTO salvar(UsuarioGatewayDTO usuario) {
        UsuarioEntity e = mapper.toEntity(usuario);
        e = repo.save(e);
        return mapper.toDomain(e);
    }

    @Override
    public Optional<UsuarioGatewayDTO> buscarPorEmail(String email) {
        return repo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<UsuarioGatewayDTO> buscarPorId(UUID id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    public Optional<UsuarioGatewayDTO> buscarPorNome(String nome) {
        return repo.findByNome(nome).map(mapper::toDomain);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existsById(id);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public boolean existePorLogin(String login) {
        return repo.existsByLogin(login);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<UsuarioGatewayDTO> buscarPorLogin(String login) {
        return repo.findByLogin(login).map(mapper::toDomain);
    }
}
