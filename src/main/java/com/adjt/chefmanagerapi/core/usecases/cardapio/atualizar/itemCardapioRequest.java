package com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class itemCardapioRequest {
    private String nome; // opcional
    private String descricao; // opcional
    private BigDecimal preco; // opcional
    private Boolean consumoLocal; // opcional
    private String caminhoFoto; // opcional
    private UUID idRestaurante;
}
