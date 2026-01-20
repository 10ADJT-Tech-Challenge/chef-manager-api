package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.UsuarioRepositoryGateway;

import java.util.Optional;
import java.util.UUID;

public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepositoryGateway repo;

    private final UsuarioGatewayMapper mapper;

    public UsuarioGatewayImpl(UsuarioRepositoryGateway repo, UsuarioGatewayMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return mapper.toDomain(
                repo.salvar(mapper.toGatewayDTO(usuario))
        );
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.buscarPorEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return repo.buscarPorId(id).map(mapper::toDomain);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existePorId(id);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repo.existePorEmail(email);
    }

    @Override
    public boolean existePorLogin(String login) {
        return repo.existePorLogin(login);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deletarPorId(id);
    }

    @Override
    public boolean existeComTipoUsuario(UUID id) {
        return repo.existeComTipoUsuario(id);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return repo.buscarPorLogin(login).map(mapper::toDomain);
    }
}
