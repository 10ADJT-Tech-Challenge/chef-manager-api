package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



class ItemCardapioTest {
    private static final String NOME = "Hamburguer double Chesse";
    private static final String DESCRICAO = "hamburguer de grão de bico com queijo de castanha";
    private static final BigDecimal VALOR = new BigDecimal(50);
    private static final Boolean CONSUMO_LOCAL = true;
    private static final String CAMINHO_FOTO = "www.google.com";


    private static final Usuario USUARIO = new Administrador(
            UUID.randomUUID(),
            "Nome",
            "email@email.com",
            "login",
            "senha123",
            null,
            null
    );

    private static final Restaurante restaurante = new Restaurante(
            UUID.randomUUID(),
            "Ahimsa",
            "Rua das gaivotas 12",
            "Vegana",
            "17 a 23",
            USUARIO
    );


    @Test
    void deveCriarItemCardapioComSucesso() {
        // arrange & act
        ItemCardapio itemCardapio = new ItemCardapio("Hamburguer double Chesse","hamburguer de grão de bico com queijo de castanha", new BigDecimal(50),true,"www.google.com",restaurante.getId());

        // assert
        assertNotNull(itemCardapio);
        assertNotNull(itemCardapio.getId());
        assertEquals(NOME, itemCardapio.getNome());
        assertEquals(DESCRICAO, itemCardapio.getDescricao());
        assertEquals(0, VALOR.compareTo(itemCardapio.getPreco()));
        assertEquals(CONSUMO_LOCAL, itemCardapio.isConsumoLocal());
        assertEquals(CAMINHO_FOTO, itemCardapio.getCaminhoFoto());
        assertEquals(restaurante.getId(), itemCardapio.getRestauranteId());
    }
}
