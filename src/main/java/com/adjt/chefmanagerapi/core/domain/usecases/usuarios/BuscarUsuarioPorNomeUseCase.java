package com.adjt.chefmanagerapi.core.domain.usecases.usuarios;

import com.adjt.chefmanagerapi.core.adapters.mappers.UsuarioMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.core.adapters.interfaces.BuscarUsuarioPorNome;
import com.adjt.chefmanagerapi.core.adapters.gateways.UsuarioGateway;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuscarUsuarioPorNomeUseCase implements BuscarUsuarioPorNome {
    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;

    public BuscarUsuarioPorNomeUseCase(UsuarioGateway usuarioGateway, UsuarioMapper mapper) {
        this.usuarioGateway = usuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public UsuarioOutput executar(String nome) {
        Optional<Usuario> optUsuario = usuarioGateway.buscarPorNome(nome);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com esse nome: " + nome);

        return mapper.toOutput(optUsuario.get());
    }
}
