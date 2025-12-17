package com.adjt.chefmanagerapi.core.usecases.usuario.deletar;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
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
    public Void executar(UUID id) {
        if (!usuarioGateway.existePorId(id))
            throw new NoSuchElementException("Nenhum usuário encontrado com o id: " + id);

        usuarioGateway.deletarPorId(id);
        return null;
    }
}