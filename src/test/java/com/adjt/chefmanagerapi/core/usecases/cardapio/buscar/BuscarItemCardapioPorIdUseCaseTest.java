package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.Builders.ItemCardapioBuilder;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class BuscarItemCardapioPorIdUseCaseTest {
    private AutoCloseable openMocks;
    @Mock private ItemCardapioGateway gateway;
    private BuscarItemCardapioPorIdUseCase useCase;

    @BeforeEach void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarItemCardapioPorIdUseCase(gateway, new ItemCardapioMapperImpl());
    }
    @AfterEach void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveRetornarQuandoEncontrado() {
        UUID id = UUID.randomUUID();
        var entidade = ItemCardapioBuilder.umItem().build();
        when(gateway.buscarPorId(id)).thenReturn(Optional.of(entidade));

        var out = useCase.executar(id);

        assertNotNull(out);
        assertEquals(entidade.getNome(), out.getNome());
    }

    @Test
    void deveLancarQuandoNaoEncontrado() {
        UUID id = UUID.randomUUID();
        when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> useCase.executar(id));
        assertTrue(ex.getMessage().contains(id.toString()));
    }
}
