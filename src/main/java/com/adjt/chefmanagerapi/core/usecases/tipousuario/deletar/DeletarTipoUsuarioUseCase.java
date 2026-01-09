package com.adjt.chefmanagerapi.core.usecases.tipousuario.deletar;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeletarTipoUsuarioUseCase implements DeletarTipoUsuario {
    public static final String MSG_NENHUM_TIPO_USUARIO_ENCONTRADO = "Nenhum tipo usuário encontrado com o id: ";
    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final UsuarioGateway usuarioGateway;

    public DeletarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Void executar(UUID id) {
        if (!tipoUsuarioGateway.isExistePorId(id))
            throw new NoSuchElementException(MSG_NENHUM_TIPO_USUARIO_ENCONTRADO + id);

        if (usuarioGateway.existeComTipoUsuario(id))
            throw new IllegalStateException("Não é possível deletar o tipo de usuário, pois existem usuários associados a ele.");

        tipoUsuarioGateway.deletarPorId(id);
        return null;
    }
}
