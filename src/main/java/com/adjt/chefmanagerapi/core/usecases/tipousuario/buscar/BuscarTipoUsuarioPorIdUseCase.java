package com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarTipoUsuarioPorIdUseCase implements BuscarTipoUsuarioPorId {
    public static final String MSG_NENHUM_TIPO_USUARIO_ENCONTRADO = "Nenhum tipo usuário encontrado com o id: ";
    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final TipoUsuarioMapper mapper;

    public BuscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway tipoUsuarioGateway, TipoUsuarioMapper mapper) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public BuscarTipoUsuarioOutput executar(UUID id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioGateway.buscaPorId(id);
        if (tipoUsuario.isEmpty())
            throw new NoSuchElementException(MSG_NENHUM_TIPO_USUARIO_ENCONTRADO + id);

        return mapper.toBuscarTipoUsuarioOutput(tipoUsuario.get());
    }
}
