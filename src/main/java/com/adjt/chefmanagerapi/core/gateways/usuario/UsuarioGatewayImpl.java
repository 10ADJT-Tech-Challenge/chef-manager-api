package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.UsuarioGatewayRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioGatewayRepository repo;

    private final UsuarioGatewayMapper mapper;

    public UsuarioGatewayImpl(UsuarioGatewayRepository repo, UsuarioGatewayMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Usuario salvar(Usuario usuario) {
        return mapper.toDomain(
                repo.salvar(mapper.toGatewayDTO(usuario))
        );
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.buscarPorEmail(email).map(mapper::toDomain);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome).map(mapper::toDomain);
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return repo.buscarPorId(id).map(mapper::toDomain);
    }

    public boolean existePorId(UUID id) {
        return repo.existePorId(id);
    }

    public void deletarPorId(UUID id) {
        repo.deletarPorId(id);
    }

    public Optional<Usuario> buscarPorLogin(String login) {
        return repo.buscarPorLogin(login).map(mapper::toDomain);
    }
}
