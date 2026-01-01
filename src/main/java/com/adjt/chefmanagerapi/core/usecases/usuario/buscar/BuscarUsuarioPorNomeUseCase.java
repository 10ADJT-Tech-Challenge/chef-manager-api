package com.adjt.chefmanagerapi.core.usecases.usuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
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
    public BuscarUsuarioOutput executar(String nome) {
        Optional<Usuario> optUsuario = usuarioGateway.buscarPorNome(nome);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com esse nome: " + nome);

        return mapper.toBuscarOutput(optUsuario.get());
    }
}
