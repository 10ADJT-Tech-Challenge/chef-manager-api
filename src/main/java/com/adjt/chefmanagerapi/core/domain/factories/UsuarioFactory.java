package com.adjt.chefmanagerapi.core.domain.factories;

import com.adjt.chefmanagerapi.core.domain.entities.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.Cliente;
import com.adjt.chefmanagerapi.core.domain.entities.DonoRestaurante;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public class UsuarioFactory {
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
            String tipo,
            String rua,
            String numero,
            String cidade,
            String cep,
            String uf
    ) {
        return getUsuario(UsuarioDTO.builder()
                .nome(nome)
                .email(email)
                .login(login)
                .senha(senha)
                .tipo(TipoUsuario.valueOf(tipo))
                .endereco(new Endereco(rua, numero, cidade, cep, uf))
                .build()
        );
    }

    public static Usuario converter(Usuario usuarioAtual, TipoUsuario novoTipo) {
        if (novoTipo == usuarioAtual.getTipo())
            return usuarioAtual;

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
        return switch (usuarioDTO.getTipo()) {
            case ADMIN -> usuarioDTO.getId() == null
                    ? new Administrador(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco())
                    : new Administrador(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco());
            case CLIENTE -> usuarioDTO.getId() == null
                    ? new Cliente(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco())
                    : new Cliente(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco());
            case DONO_RESTAURANTE -> usuarioDTO.getId() == null
                    ? new DonoRestaurante(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco())
                    : new DonoRestaurante(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getEndereco());
        };
    }
}