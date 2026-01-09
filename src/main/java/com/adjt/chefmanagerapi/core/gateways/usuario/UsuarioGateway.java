package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioGateway {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorNome(String nome);

    Optional<Usuario> buscarPorId(UUID id);

    Optional<Usuario> buscarPorLogin(String login);

    boolean existePorId(UUID id);

    boolean existePorEmail(String email);

    boolean existePorLogin(String login);

    void deletarPorId(UUID id);

    boolean existeComTipoUsuario(UUID id);
}
