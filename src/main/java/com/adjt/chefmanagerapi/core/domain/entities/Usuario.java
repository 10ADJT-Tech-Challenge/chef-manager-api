package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.valueobjects.Email;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
import com.adjt.chefmanagerapi.core.exceptions.LoginObrigatorioException;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class Usuario {
    private final UUID id;
    private String nome;
    private Email email;
    private String login;
    private String senha;
    private TipoUsuario tipo;
    private Endereco endereco;
    private OffsetDateTime dataUltimaAlteracao;

    public Usuario(String nome, String email, String login, String senha, String tipo, Endereco endereco) {
        this.id = UUID.randomUUID();
        setNome(nome);
        setEmail(email);
        setLogin(login);
        setTipo(tipo);
        setSenha(senha);
        this.endereco = endereco;
        atualizarDataAlteracao();
    }

    public void alterarSenha(String novaSenha) {
        setSenha(novaSenha);
        atualizarDataAlteracao();
    }

    private void setSenha(String novaSenha) {
        validaSenhaObrigatoria(novaSenha);
        this.senha = novaSenha;
    }

    private static void validaSenhaObrigatoria(String novaSenha) {
        if (novaSenha == null)
            throw new IllegalArgumentException("Nova senha não pode ser nula");
    }

    private void setTipo(String tipo) {
        this.tipo = TipoUsuario.valueOf(tipo);
    }

    public void atualizarTipo(String tipo) {
        setTipo(tipo);
        atualizarDataAlteracao();
    }

    public void atualizarNome(String nome) {
        setNome(nome);
        atualizarDataAlteracao();
    }

    private void setNome(String nome) {
        validaNomeObrigatorio(nome);
        this.nome = nome;
    }

    private static void validaNomeObrigatorio(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }

    public void atualizarEmail(String email) {
        setEmail(email);
        atualizarDataAlteracao();
    }

    private void setEmail(String email) {
        this.email = new Email(email);
    }

    public void atualizarLogin(String login) {
        setLogin(login);
        atualizarDataAlteracao();
    }

    private void setLogin(String login) {
        this.login = login;
        validaLoginObrigatorio(login);
    }

    private static void validaLoginObrigatorio(String login) {
        if (login == null || login.trim().isEmpty())
            throw new LoginObrigatorioException();
    }

    public void atualizarEndereco(Endereco endereco) {
        this.endereco = endereco;
        atualizarDataAlteracao();
    }

    public void atualizarDataAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }
}

