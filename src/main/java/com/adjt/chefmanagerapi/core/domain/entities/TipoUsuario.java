package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TipoUsuario {

    private final UUID id;

    private String nome;

    private final CategoriaUsuario categoriaUsuario;

    public TipoUsuario(String nome, CategoriaUsuario categoriaUsuario) {
       this(UUID.randomUUID(), nome, categoriaUsuario);
    }

    public TipoUsuario(UUID id, String nome, CategoriaUsuario categoriaUsuario) {
        this.id = id;
        this.categoriaUsuario = categoriaUsuario;
        setNome(nome);
    }

    public void atualizarNome(String novoNome) {
        setNome(novoNome);
    }

    private void setNome(String nome) {
        this.nome = nome;
    }
}
