package com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioHelper;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarTipoUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private CadastrarTipoUsuario cadastrarTipoUsuarioUseCase;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = org.mockito.MockitoAnnotations.openMocks(this);
        cadastrarTipoUsuarioUseCase = new CadastrarTipoUsuarioUseCase(tipoUsuarioGatewayMock, new TipoUsuarioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCadastrarTipoUsuarioComSucesso() {
        // Arrange
        CadastrarTipoUsuarioInput input = TipoUsuarioHelper.CadastrarTipoUsuarioHelper.criarInputClienteValido();

        when(tipoUsuarioGatewayMock.isExisteComNome(input.nome())).thenReturn(false);
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CadastrarTipoUsuarioOutput resultado = cadastrarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertNotNull(resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).isExisteComNome(input.nome());
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioJaExiste() {
        // Arrange
        CadastrarTipoUsuarioInput input = TipoUsuarioHelper.CadastrarTipoUsuarioHelper.criarInputClienteValido();
        when(tipoUsuarioGatewayMock.isExisteComNome(input.nome())).thenReturn(true);

        // Act & Assert
        TipoUsuarioJaCadastradoException tipoUsuarioJaCadastradoException = assertThrows(TipoUsuarioJaCadastradoException.class,
                () -> cadastrarTipoUsuarioUseCase.executar(input));
        assertEquals("Tipo de usuário já cadastrado: " + input.nome(), tipoUsuarioJaCadastradoException.getMessage());
        verify(tipoUsuarioGatewayMock).isExisteComNome(input.nome());
        verify(tipoUsuarioGatewayMock, never()).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveCriarTipoUsuarioDonoRestauranteCorretamente() {
        // Arrange
        CadastrarTipoUsuarioInput input = TipoUsuarioHelper.CadastrarTipoUsuarioHelper.criarInputDonoRestauranteValido();

        when(tipoUsuarioGatewayMock.isExisteComNome(input.nome())).thenReturn(false);
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CadastrarTipoUsuarioOutput resultado = cadastrarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertNotNull(resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).isExisteComNome(input.nome());
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveCriarTipoUsuarioAdminCorretamente() {
        // Arrange
        CadastrarTipoUsuarioInput input = TipoUsuarioHelper.CadastrarTipoUsuarioHelper.criarInputAdministradorValido();

        when(tipoUsuarioGatewayMock.isExisteComNome(input.nome())).thenReturn(false);
        when(tipoUsuarioGatewayMock.salvar(any(TipoUsuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CadastrarTipoUsuarioOutput resultado = cadastrarTipoUsuarioUseCase.executar(input);

        // Assert
        assertNotNull(resultado);
        assertNotNull(resultado.id());
        assertEquals(input.nome(), resultado.nome());
        assertEquals(input.categoriaUsuario(), resultado.categoriaUsuario());

        verify(tipoUsuarioGatewayMock).isExisteComNome(input.nome());
        verify(tipoUsuarioGatewayMock).salvar(any(TipoUsuario.class));
    }


}
