package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;

import java.util.UUID;

public class Cliente extends Usuario {
    public Cliente(String nome, String email, String login, String senha, Endereco endereco) {
        super(nome, email, login, senha, endereco);
    }

    public Cliente(UUID id, String nome, String email, String login, String senha, Endereco endereco) {
        super(id, nome, email, login, senha, endereco);
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.CLIENTE;
    }
}
