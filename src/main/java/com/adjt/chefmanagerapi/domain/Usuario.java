package com.adjt.chefmanagerapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private TipoUsuario tipo;
    private Endereco endereco;
    private OffsetDateTime dataUltimaAlteracao;

    public Usuario(String nome, String email, String login, String senha, TipoUsuario tipo, Endereco endereco) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.endereco = endereco;
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }
    public void alterarSenha(String novaSenha) {
        if (novaSenha == null) throw new IllegalArgumentException("Nova senha não pode ser nula");
        this.senha = novaSenha;
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }

    public void atualizarDataAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }
}

