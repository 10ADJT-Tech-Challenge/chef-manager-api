package com.adjt.chefmanagerapi.core.domain.entities.usuario;

import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;

import java.util.UUID;

public class DonoRestaurante extends Usuario {
    public DonoRestaurante(String nome, String email, String login, String senha, Endereco endereco) {
        super(nome, email, login, senha, endereco);
    }

    public DonoRestaurante(UUID id, String nome, String email, String login, String senha, Endereco endereco) {
        super(id, nome, email, login, senha, endereco);
    }

    @Override
    public CategoriaUsuario getTipo() {
        return CategoriaUsuario.DONO_RESTAURANTE;
    }
}
