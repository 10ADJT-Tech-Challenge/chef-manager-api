package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.BuscarUsuarioPorNome;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.application.mapper.UsuarioApplicationMapper;
import com.adjt.chefmanagerapi.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuscarUsuarioPorNomeUseCase implements BuscarUsuarioPorNome {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioApplicationMapper mapper;

    public BuscarUsuarioPorNomeUseCase(UsuarioRepository usuarioRepository, UsuarioApplicationMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioOutput executar(String nome) {
        Optional<Usuario> optUsuario = usuarioRepository.buscarPorNome(nome);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com esse nome: " + nome);

        return mapper.toOutput(optUsuario.get());
    }
}
