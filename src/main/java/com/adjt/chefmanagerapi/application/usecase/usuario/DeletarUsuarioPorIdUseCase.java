package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.DeletarUsuarioPorId;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeletarUsuarioPorIdUseCase implements DeletarUsuarioPorId {
    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioPorIdUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void executar(UUID id) {
        if (!usuarioRepository.existePorId(id))
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        usuarioRepository.deletarPorId(id);
    }
}