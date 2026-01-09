package com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoEncontradoException;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoPodeTrocarCategoriaException;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioHelper;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoEncontradoException.MSG_TIPO_DE_USUARIO_NAO_ENCONTRADO;
import static com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoPodeTrocarCategoriaException.MSG_TIPO_DE_USUARIO_NAO_PODE_TROCAR_DE_CATEGORIA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarTipoUsuarioUseCaseTest {

    private AutoCloseable openMocks;
    private AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = org.mockito.MockitoAnnotations.openMocks(this);
        atualizarTipoUsuarioUseCase = new AtualizarTipoUsuarioUseCase(tipoUsuarioGatewayMock, new TipoUsuarioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveAtualizarTipoUsuarioClienteComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        TipoUsuario tipoUsuarioExistente = new TipoUsuario(id, "Nome Antigo", CategoriaUsuario.CLIENTE);
        AtualizarTipoUsuarioInput input = TipoUsuarioHelper.AtualizarTipoUsuarioHelper.criarInputClienteValido(id);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.of(tipoUsuarioExistente));
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        AtualizarTipoUsuarioOutput resultado = atualizarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).buscaPorId(id);
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveAtualizarTipoUsuarioAdminComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        TipoUsuario tipoUsuarioExistente = new TipoUsuario(id, "Nome Antigo", CategoriaUsuario.ADMIN);
        AtualizarTipoUsuarioInput input = TipoUsuarioHelper.AtualizarTipoUsuarioHelper.criarInputAdministradorValido(id);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.of(tipoUsuarioExistente));
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        AtualizarTipoUsuarioOutput resultado = atualizarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).buscaPorId(id);
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveAtualizarTipoUsuarioDonoRestauranteComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        TipoUsuario tipoUsuarioExistente = new TipoUsuario(id, "Nome Antigo", CategoriaUsuario.DONO_RESTAURANTE);
        AtualizarTipoUsuarioInput input = TipoUsuarioHelper.AtualizarTipoUsuarioHelper.criarInputDonoRestauranteValido(id);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.of(tipoUsuarioExistente));
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        AtualizarTipoUsuarioOutput resultado = atualizarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).buscaPorId(id);
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        // Arrange
        UUID id = UUID.randomUUID();
        AtualizarTipoUsuarioInput input = TipoUsuarioHelper.AtualizarTipoUsuarioHelper.criarInputAdministradorValido(id);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        TipoUsuarioNaoEncontradoException exception = assertThrows(TipoUsuarioNaoEncontradoException.class,
                () -> atualizarTipoUsuarioUseCase.executar(input));
        
        assertEquals(MSG_TIPO_DE_USUARIO_NAO_ENCONTRADO + id, exception.getMessage());
        verify(tipoUsuarioGatewayMock).buscaPorId(id);
        verify(tipoUsuarioGatewayMock, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoTentarMudarCategoria() {
        // Arrange
        UUID id = UUID.randomUUID();
        TipoUsuario tipoUsuarioExistente = new TipoUsuario(id, "Nome", CategoriaUsuario.CLIENTE);
        AtualizarTipoUsuarioInput input = TipoUsuarioHelper.AtualizarTipoUsuarioHelper.criarInputDonoRestauranteValido(id);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.of(tipoUsuarioExistente));

        // Act & Assert
        TipoUsuarioNaoPodeTrocarCategoriaException exception = assertThrows(TipoUsuarioNaoPodeTrocarCategoriaException.class,
                () -> atualizarTipoUsuarioUseCase.executar(input));

        assertEquals(MSG_TIPO_DE_USUARIO_NAO_PODE_TROCAR_DE_CATEGORIA, exception.getMessage());
        verify(tipoUsuarioGatewayMock).buscaPorId(id);
        verify(tipoUsuarioGatewayMock, never()).salvar(any());
    }
}