package com.adjt.chefmanagerapi.core.domain.entities.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;

import java.util.UUID;

public class Cliente extends Usuario {
    public Cliente(String nome, String email, String login, String senha, Endereco endereco, TipoUsuario tipoUsuario) {
        super(nome, email, login, senha, endereco, tipoUsuario);
    }

    public Cliente(UUID id, String nome, String email, String login, String senha, Endereco endereco, TipoUsuario tipoUsuario) {
        super(id, nome, email, login, senha, endereco, tipoUsuario);
    }
}
