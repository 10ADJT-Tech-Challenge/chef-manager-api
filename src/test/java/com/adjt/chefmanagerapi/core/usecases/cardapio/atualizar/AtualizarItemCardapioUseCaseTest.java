package com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.Builders.ItemCardapioBuilder;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarItemCardapioUseCaseTest {
    private AutoCloseable openMocks;

    @Mock private ItemCardapioGateway gateway;
    private AtualizarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new AtualizarItemCardapioUseCase(gateway, new ItemCardapioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveAtualizarComSucesso() {
        UUID id = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        var existente = ItemCardapioBuilder.umItem().comRestaurante(restauranteId).build();
        var input = new AtualizarItemCardapioInput(id, "Novo Nome", "Nova desc", new BigDecimal("25.00"), false, "foto2.png",restauranteId);

        when(gateway.buscarPorId(id)).thenReturn(Optional.of(existente));

        assertDoesNotThrow(() -> useCase.executar(input));

        ArgumentCaptor<ItemCardapio> captor = ArgumentCaptor.forClass(ItemCardapio.class);
        verify(gateway).atualizar(captor.capture());
        ItemCardapio salvo = captor.getValue();

        assertEquals("Novo Nome", salvo.getNome());
        assertEquals(0, salvo.getPreco().compareTo(new BigDecimal("25.00")));
    }

    @Test
    void deveLancarQuandoItemNaoExiste() {
        UUID id = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        var input = new AtualizarItemCardapioInput(id, "Nome", "desc", new BigDecimal("10.00"), true, "foto.png",restauranteId);

        when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> useCase.executar(input));
        assertTrue(ex.getMessage().contains(id.toString()));
        verify(gateway, never()).salvar(any());
    }
}

