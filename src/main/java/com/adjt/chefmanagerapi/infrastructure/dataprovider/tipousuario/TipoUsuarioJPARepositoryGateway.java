package com.adjt.chefmanagerapi.infrastructure.dataprovider.tipousuario;

import com.adjt.chefmanagerapi.core.gateways.interfaces.TipoUsuarioRepositoryGateway;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGatewayDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TipoUsuarioJPARepositoryGateway implements TipoUsuarioRepositoryGateway {
    private final TipoUsuarioJPARepository repo;
    private final TipoUsuarioPersistenceMapper mapper;

    public TipoUsuarioJPARepositoryGateway(TipoUsuarioJPARepository repo, TipoUsuarioPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public TipoUsuarioGatewayDTO salvar(TipoUsuarioGatewayDTO tipoUsuarioGatewayDTO) {
        TipoUsuarioEntity e = mapper.toEntity(tipoUsuarioGatewayDTO);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Override
    public boolean isExisteComNome(String nome) {
        return repo.existsByNome(nome);
    }

    @Override
    public Optional<TipoUsuarioGatewayDTO> buscaPorId(UUID id) {
        return repo.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public Iterable<TipoUsuarioGatewayDTO> buscarTodos() {
        return repo.findAll(Sort.by("id")).stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public boolean isExistePorId(UUID id) {
        return repo.existsById(id);
    }

    @Override
    public void deletarPorId(UUID id) {
        repo.deleteById(id);
    }
}
