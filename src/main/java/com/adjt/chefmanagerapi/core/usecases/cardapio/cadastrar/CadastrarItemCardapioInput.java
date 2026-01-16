package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CadastrarItemCardapioInput {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean apenasNoRestaurante;
    private String caminhoFoto; // opcional
    private UUID idRestaurante;
}
