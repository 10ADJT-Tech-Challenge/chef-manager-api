package com.adjt.chefmanagerapi.core.usecases.cardapio.Builders;


import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;

import java.math.BigDecimal;
import java.util.UUID;

// test/.../builders/ItemCardapioBuilder.java
public class ItemCardapioBuilder {
    private UUID restauranteId = UUID.randomUUID();
    private String nome = "Hamburguer double Cheese";
    private String descricao = "hamburguer de grão de bico com queijo de castanha";
    private BigDecimal preco = new BigDecimal("50.00");
    private Boolean consumoLocal = true;
    private String caminhoFoto = "https://exemplo.com/img.png";

    public static ItemCardapioBuilder umItem() { return new ItemCardapioBuilder(); }
    public ItemCardapioBuilder comRestaurante(UUID r) { this.restauranteId = r; return this; }
    public ItemCardapioBuilder comNome(String n) { this.nome = n; return this; }
    public ItemCardapio build() {
        // se precisar setar id via reflexão ou construtor alternativo, ajuste aqui
        return new ItemCardapio(nome, descricao, preco, consumoLocal, caminhoFoto, restauranteId);
    }
}
