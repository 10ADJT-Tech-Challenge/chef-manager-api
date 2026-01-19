package com.adjt.chefmanagerapi.infraestructure.api.controller.cardapio;

import com.adjt.chefmanagerapi.model.ItemCardapioRequest;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class ItemCardapioRequestHelper {

    // Reaproveite os UUIDs de restaurante já semeados nos testes
    static final String UUID_DONO = "cec64cf0-6dc9-4b4e-b0b8-405870ae1b43";

    static ItemCardapioRequest novoXburger() {
        return new ItemCardapioRequest()
                .nome("X-Burger")
                .descricao("Hambúrguer artesanal")
                .preco(BigDecimal.valueOf(29.90))
                .consumoLocal(true)
                .caminhoFoto("https://img.example/xburger.png")
                .restauranteId(UUID.fromString(UUID_DONO));
    }

    static ItemCardapioRequest novoVegano() {
        return new ItemCardapioRequest()
                .nome("Vegano")
                .descricao("Grão de bico + queijo de castanhas")
                .preco(BigDecimal.valueOf(32.90))
                .consumoLocal(true)
                .caminhoFoto("https://img.example/vegano.png")
                .restauranteId(UUID.fromString(UUID_DONO));
    }

    static ItemCardapioRequest invalidoSemNome() {
        return new ItemCardapioRequest()
                .nome(null)
                .descricao("Desc")
                .preco(BigDecimal.valueOf(10.0))
                .consumoLocal(true)
                .caminhoFoto("foto.png")
                .restauranteId(UUID.fromString(UUID_DONO));
    }
}

