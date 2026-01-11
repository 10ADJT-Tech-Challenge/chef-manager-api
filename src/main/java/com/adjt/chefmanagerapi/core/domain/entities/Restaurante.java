package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class Restaurante {
    private UUID id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Usuario responsavel;
    private OffsetDateTime dataUltimaAlteracao;

    public Restaurante(String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Usuario responsavel) {
        this(UUID.randomUUID(), nome, endereco, tipoCozinha, horarioFuncionamento, responsavel);
    }

    public Restaurante(UUID id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Usuario responsavel) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.responsavel = responsavel;
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }
}
