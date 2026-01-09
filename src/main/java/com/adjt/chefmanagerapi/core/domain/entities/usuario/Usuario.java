package com.adjt.chefmanagerapi.core.domain.entities.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Email;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.exceptions.LoginObrigatorioException;
import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
import com.adjt.chefmanagerapi.core.exceptions.SenhaObrigatoriaException;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public abstract class Usuario {

    private final UUID id;
    private String nome;
    private Email email;
    private String login;
    private String senha;
    private Endereco endereco;
    private TipoUsuario tipo;
    private OffsetDateTime dataUltimaAlteracao;

    protected Usuario(String nome, String email, String login, String senha, Endereco endereco, TipoUsuario tipo) {
        this(UUID.randomUUID(), nome, email, login, senha, endereco, tipo);
    }

    protected Usuario(UUID id, String nome, String email, String login, String senha, Endereco endereco, TipoUsuario tipo) {
        setNome(nome);
        setEmail(email);
        setLogin(login);
        setSenha(senha);
        setTipo(tipo);
        setEndereco(endereco);
        this.id = id;
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
        if (novaSenha == null || novaSenha.trim().isEmpty())
            throw new SenhaObrigatoriaException();
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
            throw new NomeObrigatorioException();
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
        setEndereco(endereco);
        atualizarDataAlteracao();
    }

    private void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    private void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Usuario atualizarTipo(TipoUsuario novoTipo) {
        if (!getTipo().equals(novoTipo))
            return UsuarioFactory.converter(this, novoTipo);

        tipo = novoTipo;
        atualizarDataAlteracao();
        return this;
    }

    public void atualizarDataAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }
}

