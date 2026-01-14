package com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteHelper;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarRestaurantePorIdUseCaseTest {
    private AutoCloseable openMocks;

    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        buscarRestaurantePorIdUseCase = new BuscarRestaurantePorIdUseCase(restauranteGateway, new RestauranteMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarRestaurantePorIdComSucesso() {
        // arrange
        Restaurante restaurante = RestauranteHelper.buscaRestaurante();
        when(restauranteGateway.buscarPorId(restaurante.getId())).thenReturn(Optional.of(restaurante));

        // act
        RestauranteOutput restauranteOutput = buscarRestaurantePorIdUseCase.executar(restaurante.getId());

        // assert
        assertNotNull(restauranteOutput);
        assertEquals(restaurante.getId(), restauranteOutput.id());
        assertEquals(restaurante.getNome(), restauranteOutput.nome());
        assertEquals(restaurante.getEndereco(), restauranteOutput.endereco());
        assertEquals(restaurante.getTipoCozinha(), restauranteOutput.tipoCozinha());
        assertEquals(restaurante.getHorarioFuncionamento(), restauranteOutput.horarioFuncionamento());

        assertNotNull(restauranteOutput.responsavel());
        assertEquals(restaurante.getResponsavel().getId(), restauranteOutput.responsavel());

        verify(restauranteGateway).buscarPorId(restaurante.getId());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        // arrange
        when(restauranteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.empty());
        UUID input = UUID.randomUUID();

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> buscarRestaurantePorIdUseCase.executar(input));

        assertEquals("Nenhum restaurante encontrado com o id: " + input,
                noSuchElementException.getMessage());
        verify(restauranteGateway).buscarPorId(input);
    }
}