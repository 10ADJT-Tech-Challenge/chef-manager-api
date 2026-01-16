package com.adjt.chefmanagerapi.core.usecases.cardapio;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class ItemCardapioOutput {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean consumoLocal;
    private String caminhoFoto;
    private OffsetDateTime dataUltimaAlteracao;
    private UUID idRestaurante;
}
