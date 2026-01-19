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

class ListarItensCardapioPorIdRestauranteUseCaseTest {
    private AutoCloseable openMocks;
    @Mock private ItemCardapioGateway gateway;
    private ListarItensCardapioPorIdRestauranteUseCase useCase;


    @BeforeEach void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new ListarItensCardapioPorIdRestauranteUseCase(gateway, new ItemCardapioMapperImpl());
    }
    @AfterEach void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveListarComSucesso() {
        UUID restauranteId = UUID.randomUUID();
        when(gateway.findAllByIdRestauranteOrderByNomeAsc(restauranteId)).thenReturn(List.of(
                ItemCardapioBuilder.umItem().comRestaurante(restauranteId).build()
        ));

        var out = useCase.executar(restauranteId);
        assertNotNull(out);
        assertFalse(out.isEmpty());
    }

    @Test
    void deveRetornarListaVazia() {
        UUID restauranteId = UUID.randomUUID();
        when(gateway.findAllByIdRestauranteOrderByNomeAsc(restauranteId)).thenReturn(List.of());

        var out = useCase.executar(restauranteId);
        assertNotNull(out);
        assertTrue(out.isEmpty());
    }
}

