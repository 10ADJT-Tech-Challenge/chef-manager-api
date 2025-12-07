package com.adjt.chefmanagerapi.core.domain.usecases.usuarios;

import com.adjt.chefmanagerapi.core.adapters.interfaces.usuario.DeletarUsuarioPorId;
import com.adjt.chefmanagerapi.core.adapters.gateways.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeletarUsuarioPorIdUseCase implements DeletarUsuarioPorId {
    private final UsuarioGateway usuarioGateway;

    public DeletarUsuarioPorIdUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(UUID id) {
        if (!usuarioGateway.existePorId(id))
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        usuarioGateway.deletarPorId(id);
    }
}