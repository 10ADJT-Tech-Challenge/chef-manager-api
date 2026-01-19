package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;


import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarItemCardapioUseCaseTest {
    private AutoCloseable openMocks;

    @Mock
    private ItemCardapioGateway itemGateway;
    @Mock private RestauranteGateway restauranteGateway;

    private CadastrarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CadastrarItemCardapioUseCase(itemGateway, new ItemCardapioMapperImpl(),restauranteGateway);
    }

    @AfterEach
    void tearDown() throws Exception { openMocks.close(); }

    @Test
    void deveCadastrarItemComSucesso() {
        // arrange
        UUID restauranteId = UUID.randomUUID();
        var input = new CadastrarItemCardapioInput("X-Burger", "desc", new BigDecimal("32.90"), true, "foto.png", restauranteId);

        when(restauranteGateway.existePorId(restauranteId)).thenReturn(true);
        ArgumentCaptor<ItemCardapio> captor = ArgumentCaptor.forClass(ItemCardapio.class);

        // act
        assertDoesNotThrow(() -> useCase.executar(input));

        // assert
        verify(restauranteGateway).existePorId(restauranteId);
        verify(itemGateway).salvar(captor.capture());

        ItemCardapio salvo = captor.getValue();
        assertEquals("X-Burger", salvo.getNome());
        assertEquals(0, salvo.getPreco().compareTo(new BigDecimal("32.90")));
        assertEquals(restauranteId, salvo.getIdRestaurante());
        verifyNoMoreInteractions(itemGateway, restauranteGateway);
    }

    @Test
    void deveLancarQuandoRestauranteNaoExiste() {
        // arrange
        UUID restauranteId = UUID.randomUUID();
        var input = new CadastrarItemCardapioInput("X-Burger", "desc", new BigDecimal("32.90"), true, "foto.png", restauranteId);
        when(restauranteGateway.existePorId(restauranteId)).thenReturn(false);

        // act & assert
        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> useCase.executar(input));
        assertTrue(ex.getMessage().contains(restauranteId.toString()));

        verify(restauranteGateway).existePorId(restauranteId);
        verify(itemGateway, never()).salvar(any());
    }
}
