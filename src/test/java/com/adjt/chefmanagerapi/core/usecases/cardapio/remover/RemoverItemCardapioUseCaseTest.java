package com.adjt.chefmanagerapi.core.usecases.cardapio.remover;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoverItemCardapioUseCaseTest {
    private AutoCloseable openMocks;
    @Mock private ItemCardapioGateway gateway;
    private RemoverItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new RemoverItemCardapioUseCase(gateway);
    }

    @AfterEach
    void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveRemoverComSucesso() {
        UUID id = UUID.randomUUID();
        when(gateway.existePorId(id)).thenReturn(true);

        assertDoesNotThrow(() -> useCase.executar(id));
        verify(gateway).remover(id);
    }

    @Test
    void deveLancarQuandoNaoExiste() {
        UUID id = UUID.randomUUID();
        when(gateway.existePorId(id)).thenReturn(false);

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> useCase.executar(id));
        assertTrue(ex.getMessage().contains(id.toString()));
        verify(gateway, never()).remover(any());
    }
}

