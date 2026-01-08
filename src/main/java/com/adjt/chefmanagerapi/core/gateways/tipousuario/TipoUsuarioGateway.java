package com.adjt.chefmanagerapi.core.gateways.tipousuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;

import java.util.Optional;
import java.util.UUID;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    boolean isExisteComNome(String nome);

    Optional<TipoUsuario> buscaPorId(UUID id);

    Iterable<TipoUsuario> buscarTodos();

    boolean isExistePorId(UUID id);

    void deletarPorId(UUID id);
}
