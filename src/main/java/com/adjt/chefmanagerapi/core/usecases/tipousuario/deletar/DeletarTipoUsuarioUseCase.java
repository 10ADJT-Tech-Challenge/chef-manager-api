package com.adjt.chefmanagerapi.core.usecases.tipousuario.deletar;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeletarTipoUsuarioUseCase implements DeletarTipoUsuario {
    public static final String MSG_NENHUM_TIPO_USUARIO_ENCONTRADO = "Nenhum tipo usuário encontrado com o id: ";
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public DeletarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Void executar(UUID id) {
        if (!tipoUsuarioGateway.isExistePorId(id))
            throw new NoSuchElementException(MSG_NENHUM_TIPO_USUARIO_ENCONTRADO + id);

        tipoUsuarioGateway.deletarPorId(id);
        return null;
    }
}
