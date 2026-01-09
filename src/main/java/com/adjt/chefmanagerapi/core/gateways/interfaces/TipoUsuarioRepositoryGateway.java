package com.adjt.chefmanagerapi.core.gateways.interfaces;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGatewayDTO;

import java.util.Optional;
import java.util.UUID;

public interface TipoUsuarioRepositoryGateway {

    TipoUsuarioGatewayDTO salvar (TipoUsuarioGatewayDTO tipoUsuarioGatewayDTO);

    boolean isExisteComNome(String nome);

    Optional<TipoUsuarioGatewayDTO> buscaPorId(UUID id);

    Iterable<TipoUsuarioGatewayDTO> buscarTodos();

    boolean isExistePorId(UUID id);

    void deletarPorId(UUID id);
}
