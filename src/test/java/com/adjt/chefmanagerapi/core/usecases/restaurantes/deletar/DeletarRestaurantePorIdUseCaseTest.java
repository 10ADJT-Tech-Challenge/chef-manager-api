package com.adjt.chefmanagerapi.core.usecases.restaurantes.deletar;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeletarRestaurantePorIdUseCaseTest {
    private AutoCloseable openMocks;

    private DeletarRestaurantePorIdUseCase deletarRestaurantePorIdUseCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        deletarRestaurantePorIdUseCase = new DeletarRestaurantePorIdUseCase(restauranteGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveDeletarRestaurantePorIdComSucesso() {
        // arrange
        when(restauranteGateway.existePorId(any(UUID.class))).thenReturn(true);
        UUID id = UUID.randomUUID();

        // act
        deletarRestaurantePorIdUseCase.executar(id);

        // assert
        verify(restauranteGateway).existePorId(id);
        verify(restauranteGateway).deletarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        // arrange
        when(restauranteGateway.existePorId(any(UUID.class))).thenReturn(false);
        UUID id = UUID.randomUUID();

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> deletarRestaurantePorIdUseCase.executar(id));

        assertEquals("Nenhum restaurante encontrado com o id: " + id, noSuchElementException.getMessage());
        verify(restauranteGateway).existePorId(id);
        verify(restauranteGateway, never()).deletarPorId(any(UUID.class));
    }
}