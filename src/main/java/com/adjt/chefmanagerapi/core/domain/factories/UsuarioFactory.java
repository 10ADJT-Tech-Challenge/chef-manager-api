package com.adjt.chefmanagerapi.core.domain.factories;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Cliente;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.DonoRestaurante;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public class UsuarioFactory {
    private UsuarioFactory() {
        throw new IllegalStateException("Classe de utilitário não deve ser instanciada");
    }

    @Data
    @Builder
    private static class UsuarioDTO {
        private UUID id;
        private String nome;
        private String email;
        private String login;
        private String senha;
        private TipoUsuario tipo;
        private Endereco endereco;
    }

    public static Usuario criarUsuario(
            String nome,
            String email,
            String login,
            String senha,
            TipoUsuario tipo,
            Endereco endereco
    ) {
        return getUsuario(UsuarioDTO.builder()
                .nome(nome)
                .email(email)
                .login(login)
                .senha(senha)
                .tipo(tipo)
                .endereco(endereco)
                .build()
        );
    }

    public static Usuario criarUsuario(
            UUID id,
            String nome,
            String email,
            String login,
            String senha,
            TipoUsuario tipo,
            Endereco endereco
    ) {
        return getUsuario(UsuarioDTO.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .login(login)
                .senha(senha)
                .tipo(tipo)
                .endereco(endereco)
                .build()
        );
    }

    public static Usuario converter(Usuario usuarioAtual, TipoUsuario novoTipo) {
        return getUsuario(UsuarioDTO.builder()
                .id(usuarioAtual.getId())
                .nome(usuarioAtual.getNome())
                .email(usuarioAtual.getEmail().toString())
                .login(usuarioAtual.getLogin())
                .senha(usuarioAtual.getSenha())
                .tipo(novoTipo)
                .endereco(usuarioAtual.getEndereco())
                .build()
        );
    }

    private static Usuario getUsuario(UsuarioDTO usuarioDTO) {
        return switch (usuarioDTO.getTipo().getCategoriaUsuario()) {
            case ADMIN -> usuarioDTO.getId() == null
                    ? new Administrador(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo())
                    : new Administrador(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo());
            case CLIENTE -> usuarioDTO.getId() == null
                    ? new Cliente(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo())
                    : new Cliente(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo());
            case DONO_RESTAURANTE -> usuarioDTO.getId() == null
                    ? new DonoRestaurante(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo())
                    : new DonoRestaurante(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco(), usuarioDTO.getTipo());
        };
    }
}