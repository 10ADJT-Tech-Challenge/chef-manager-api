package com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteHelper;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarRestauranteUseCaseTest {
    private AutoCloseable openMocks;

    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        cadastrarRestauranteUseCase = new CadastrarRestauranteUseCase(restauranteGateway, new RestauranteMapperImpl(), usuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCriarRestauranteComDadosValidos() {
        CadastrarRestauranteInput input = RestauranteHelper.CadastrarRestauranteHelper.criarInputValido();
        when(usuarioGateway.buscarPorId(input.responsavel())).thenReturn(Optional.of(RestauranteHelper.donoRestaurante));
        when(restauranteGateway.salvar(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        var output = cadastrarRestauranteUseCase.executar(input);

        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.endereco(), output.endereco());
        assertEquals(input.tipoCozinha(), output.tipoCozinha());
        assertEquals(input.horarioFuncionamento(), output.horarioFuncionamento());

        verify(usuarioGateway).buscarPorId(input.responsavel());
        verify(restauranteGateway).salvar(any(Restaurante.class));
    }

    @Test
    void deveValidarRestauranteComNomeNull() {
        CadastrarRestauranteInput input = RestauranteHelper.CadastrarRestauranteHelper.criarInputComNomeNull();
        when(usuarioGateway.buscarPorId(input.responsavel())).thenReturn(Optional.of(RestauranteHelper.donoRestaurante));
        when(restauranteGateway.salvar(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        NomeObrigatorioException nomeObrigatorioException = assertThrows(NomeObrigatorioException.class, () -> cadastrarRestauranteUseCase.executar(input));

        assertEquals("Nome não pode ser nulo ou vazio", nomeObrigatorioException.getMessage());
        verifyNoInteractions(restauranteGateway);
    }
}