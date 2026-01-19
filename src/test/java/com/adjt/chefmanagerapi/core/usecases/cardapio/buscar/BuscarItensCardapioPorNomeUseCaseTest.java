package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.Builders.ItemCardapioBuilder;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BuscarItensCardapioPorNomeUseCaseTest {
    private AutoCloseable openMocks;
    @Mock private ItemCardapioGateway gateway;
    private BuscarItensCardapioPorNomeUseCase useCase;

    @BeforeEach void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarItensCardapioPorNomeUseCase(gateway, new ItemCardapioMapperImpl());
    }
    @AfterEach void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveBuscarGlobalmente() {
        String term = "vegano";
        when(gateway.buscarPorNome(term)).thenReturn(List.of(
                ItemCardapioBuilder.umItem().comNome("Vegano Especial").build()
        ));

        var out = useCase.executar(term);
        assertFalse(out.isEmpty());
        assertTrue(out.get(0).getNome().toLowerCase().contains("vegano"));
    }

    @Test
    void deveRetornarVazio() {
        String term = "xyz";
        when(gateway.buscarPorNome(term)).thenReturn(List.of());

        var out = useCase.executar(term);
        assertTrue(out.isEmpty());
    }
}
