package com.adjt.chefmanagerapi.core.usecases.tipousuario.deletar;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeletarTipoUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private DeletarTipoUsuario useCase;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGatewayMock;

    @Mock
    private UsuarioGateway usuarioGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = org.mockito.MockitoAnnotations.openMocks(this);
        useCase = new DeletarTipoUsuarioUseCase(tipoUsuarioGatewayMock, usuarioGatewayMock);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveDeletarTipoUsuarioQuandoIdExistir() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(tipoUsuarioGatewayMock.isExistePorId(id)).thenReturn(true);
        when(usuarioGatewayMock.existeComTipoUsuario(id)).thenReturn(false);

        // Act
        useCase.executar(id);

        // Assert
        verify(tipoUsuarioGatewayMock).isExistePorId(id);
        verify(usuarioGatewayMock).existeComTipoUsuario(id);
        verify(tipoUsuarioGatewayMock).deletarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(tipoUsuarioGatewayMock.isExistePorId(id)).thenReturn(false);

       // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> useCase.executar(id)
        );

        assertEquals(DeletarTipoUsuarioUseCase.MSG_NENHUM_TIPO_USUARIO_ENCONTRADO + id, exception.getMessage());
        verify(tipoUsuarioGatewayMock).isExistePorId(id);
        verify(usuarioGatewayMock, never()).existeComTipoUsuario(id);
        verify(tipoUsuarioGatewayMock, never()).deletarPorId(any());
    }

    @Test
    void deveLancarExcecaoQuandoExistirUsuarioVinculadoAoTipo() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(tipoUsuarioGatewayMock.isExistePorId(id)).thenReturn(true);
        when(usuarioGatewayMock.existeComTipoUsuario(id)).thenReturn(true);

       // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> useCase.executar(id)
        );

        assertEquals("Não é possível deletar o tipo de usuário, pois existem usuários associados a ele.", exception.getMessage());
        verify(tipoUsuarioGatewayMock).isExistePorId(id);
        verify(usuarioGatewayMock).existeComTipoUsuario(id);
        verify(tipoUsuarioGatewayMock, never()).deletarPorId(any());
    }
}