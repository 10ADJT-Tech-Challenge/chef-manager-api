package com.adjt.chefmanagerapi.core.gateways.interfaces;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayDTO;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioGatewayRepository {
    UsuarioGatewayDTO salvar(UsuarioGatewayDTO usuario);

    Optional<UsuarioGatewayDTO> buscarPorEmail(String email);

    Optional<UsuarioGatewayDTO> buscarPorNome(String nome);

    Optional<UsuarioGatewayDTO> buscarPorId(UUID id);

    boolean existePorId(UUID id);

    void deletarPorId(UUID id);

    Optional<UsuarioGatewayDTO> buscarPorLogin(String login);
}
