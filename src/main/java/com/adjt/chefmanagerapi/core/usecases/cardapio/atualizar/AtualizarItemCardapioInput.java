package com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


public record AtualizarItemCardapioInput(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean consumoLocal,   // opcional para “não alterar”
        String caminhoFoto,
        UUID idRestaurante      // opcional (se permitir mudar)
) {}
