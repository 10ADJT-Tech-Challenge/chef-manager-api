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
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BuscarItensCardapioPorNomeNoRestauranteUseCaseTest {
    private AutoCloseable openMocks;
    @Mock private ItemCardapioGateway gateway;
    private BuscarItensCardapioPorNomeNoRestauranteUseCase useCase;

    @BeforeEach void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarItensCardapioPorNomeNoRestauranteUseCase(gateway, new ItemCardapioMapperImpl());
    }
    @AfterEach void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveBuscarPorTermoComSucesso() {
        UUID restauranteId = UUID.randomUUID();
        String term = "burger";

        when(gateway.buscarPorNomeNoRestaurante(restauranteId, term))
                .thenReturn(List.of(ItemCardapioBuilder.umItem().comRestaurante(restauranteId).build()));

        var input = new BuscarItensCardapioPorNomeNoRestauranteInput(restauranteId, term);
        var out = useCase.executar(input);

        assertFalse(out.isEmpty());
        verify(gateway).buscarPorNomeNoRestaurante(restauranteId, term);
    }

    @Test
    void deveRetornarVazioQuandoNadaEncontrado() {
        UUID restauranteId = UUID.randomUUID();
        String term = "inexistente";
        when(gateway.buscarPorNomeNoRestaurante(restauranteId, term)).thenReturn(List.of());

        var out = useCase.executar(new BuscarItensCardapioPorNomeNoRestauranteInput(restauranteId, term));
        assertTrue(out.isEmpty());
    }
}

