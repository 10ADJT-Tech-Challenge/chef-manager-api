package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
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
        setNome(nome);
        setEndereco(endereco);
        setTipoCozinha(tipoCozinha);
        setHorarioFuncionamento(horarioFuncionamento);
        setResponsavel(responsavel);
        setDataUltimaAlteracao();
    }

    public void setNome(String nome) {
        validaNomeObrigatorio(nome);
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public void setDataUltimaAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }

    private static void validaNomeObrigatorio(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new NomeObrigatorioException();
    }
}
