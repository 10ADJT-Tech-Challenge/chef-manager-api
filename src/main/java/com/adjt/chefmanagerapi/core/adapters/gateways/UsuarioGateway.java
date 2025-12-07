package com.adjt.chefmanagerapi.core.adapters.gateways;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioGateway {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorNome(String nome);

    Optional<Usuario> buscarPorId(UUID id);

    boolean existePorId(UUID id);

    void deletarPorId(UUID id);

    Optional<Usuario> buscarPorLogin(String login);
}
