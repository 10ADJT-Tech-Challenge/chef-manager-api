package com.adjt.chefmanagerapi.core.domain.usecases.usuarios;

import com.adjt.chefmanagerapi.core.adapters.mappers.UsuarioMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.core.adapters.interfaces.BuscarUsuarioPorId;
import com.adjt.chefmanagerapi.core.adapters.gateways.UsuarioGateway;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarUsuarioPorIdUseCase implements BuscarUsuarioPorId {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;

    public BuscarUsuarioPorIdUseCase(UsuarioGateway usuarioGateway, UsuarioMapper mapper) {
        this.usuarioGateway = usuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public UsuarioOutput executar(UUID id) {
        Optional<Usuario> optUsuario = usuarioGateway.buscarPorId(id);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        return mapper.toOutput(optUsuario.get());
    }
}
