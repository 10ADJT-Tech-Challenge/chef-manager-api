package com.adjt.chefmanagerapi.core.usecases.usuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
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
    public BuscarUsuarioOutput executar(UUID id) {
        Optional<Usuario> optUsuario = usuarioGateway.buscarPorId(id);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        return mapper.toBuscarOutput(optUsuario.get());
    }
}
