package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.BuscarUsuarioPorId;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.application.mapper.UsuarioApplicationMapper;
import com.adjt.chefmanagerapi.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarUsuarioPorIdUseCase implements BuscarUsuarioPorId {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioApplicationMapper mapper;

    public BuscarUsuarioPorIdUseCase(UsuarioRepository usuarioRepository, UsuarioApplicationMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioOutput executar(UUID id) {
        Optional<Usuario> optUsuario = usuarioRepository.buscarPorId(id);
        if (optUsuario.isEmpty())
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        return mapper.toOutput(optUsuario.get());
    }
}
