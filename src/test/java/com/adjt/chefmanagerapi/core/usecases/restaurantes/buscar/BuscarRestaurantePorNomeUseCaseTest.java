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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarRestaurantePorNomeUseCaseTest {
    private AutoCloseable openMocks;

    private BuscarRestaurantePorNomeUseCase buscarRestaurantePorNomeUseCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        buscarRestaurantePorNomeUseCase = new BuscarRestaurantePorNomeUseCase(restauranteGateway, new RestauranteMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarRestaurantePorNomeComSucesso() {
        // arrange
        Restaurante restaurante = RestauranteHelper.buscaRestaurante();
        when(restauranteGateway.buscarPorNome(restaurante.getNome())).thenReturn(Optional.of(restaurante));

        // act
        List<RestauranteOutput> restaurantesOutput = buscarRestaurantePorNomeUseCase.executar(restaurante.getNome());
        RestauranteOutput restauranteOutput = restaurantesOutput.getFirst();

        // assert
        assertNotNull(restaurantesOutput);
        assertEquals(restaurante.getId(), restauranteOutput.id());
        assertEquals(restaurante.getNome(), restauranteOutput.nome());
        assertEquals(restaurante.getEndereco(), restauranteOutput.endereco());
        assertEquals(restaurante.getTipoCozinha(), restauranteOutput.tipoCozinha());
        assertEquals(restaurante.getHorarioFuncionamento(), restauranteOutput.horarioFuncionamento());

        assertNotNull(restauranteOutput.responsavel());
        assertEquals(restaurante.getResponsavel().getId(), restauranteOutput.responsavel());

        verify(restauranteGateway).buscarPorNome(restaurante.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        // arrange
        when(restauranteGateway.buscarPorNome(any(String.class))).thenReturn(Optional.empty());
        String nome = "restaurante";

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> buscarRestaurantePorNomeUseCase.executar(nome));

        assertEquals("Nenhum restaurante encontrado com esse nome: " + nome,
                noSuchElementException.getMessage());
        verify(restauranteGateway).buscarPorNome(nome);
    }
}