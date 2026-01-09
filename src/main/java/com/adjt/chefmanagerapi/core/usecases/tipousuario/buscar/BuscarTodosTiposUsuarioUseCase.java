package com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class BuscarTodosTiposUsuarioUseCase implements BuscarTodosTipoUsuario {
    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final TipoUsuarioMapper mapper;

    public BuscarTodosTiposUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway, TipoUsuarioMapper mapper) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public Iterable<BuscarTipoUsuarioOutput> executar(Void input) {
        Iterable<TipoUsuario> buscarTodos = tipoUsuarioGateway.buscarTodos();
        return StreamSupport.stream(buscarTodos.spliterator(), false)
                .map(mapper::toBuscarTipoUsuarioOutput)
                .toList();
    }
}
