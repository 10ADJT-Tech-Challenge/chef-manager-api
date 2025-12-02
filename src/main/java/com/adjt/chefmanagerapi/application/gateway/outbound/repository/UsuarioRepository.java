package com.adjt.chefmanagerapi.application.gateway.outbound.repository;

import com.adjt.chefmanagerapi.domain.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorNome(String nome);

    Optional<Usuario> buscarPorId(UUID id);

    boolean existePorId(UUID id);

    void deletarPorId(UUID id);

    Optional<Usuario> buscarPorLogin(String login);
}
