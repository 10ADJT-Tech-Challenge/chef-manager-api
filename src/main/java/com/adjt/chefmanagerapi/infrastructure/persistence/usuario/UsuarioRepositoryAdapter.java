package com.adjt.chefmanagerapi.infrastructure.persistence.usuario;

import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.domain.Usuario;
import com.adjt.chefmanagerapi.infrastructure.persistence.mapper.UsuarioPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioJPARepository repo;
    private final UsuarioPersistenceMapper mapper;

    public UsuarioRepositoryAdapter(UsuarioJPARepository repo, UsuarioPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity e = mapper.toEntity(usuario);
        e = repo.save(e);
        return mapper.toDomain(e);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return repo.findByNome(nome).map(mapper::toDomain);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existsById(id);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return repo.findByLogin(login).map(mapper::toDomain);
    }
}
