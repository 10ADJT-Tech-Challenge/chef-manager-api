package com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteHelper;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {
    private AutoCloseable openMocks;

    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        atualizarRestauranteUseCase = new AtualizarRestauranteUseCase(restauranteGateway, new RestauranteMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        // arrange
        UUID restauranteId = UUID.randomUUID();
        AtualizarRestauranteInput atualizarRestauranteInput = RestauranteHelper.AtualizarRestauranteHelper.criarInputAtualizacaoCompleta(restauranteId);
        Restaurante restaurante = RestauranteHelper.criarRestauranteSimulado(atualizarRestauranteInput);
        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));

        // act
        assertDoesNotThrow(() -> atualizarRestauranteUseCase.executar(atualizarRestauranteInput));

        // assert
        verify(restauranteGateway).salvar(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        // arrange
        UUID restauranteId = UUID.randomUUID();
        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.empty());

        AtualizarRestauranteInput atualizarRestauranteInput = RestauranteHelper.AtualizarRestauranteHelper.criarInputAtualizacaoCompleta(restauranteId);
        // act & assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> atualizarRestauranteUseCase.executar(atualizarRestauranteInput));
        assertEquals("Nenhum restaurante encontrado com o id: " + restauranteId, noSuchElementException.getMessage());
        verify(restauranteGateway, never()).salvar(any(Restaurante.class));
        verify(restauranteGateway).buscarPorId(restauranteId);
    }
}