package com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTipoUsuarioPorIdUseCase.MSG_NENHUM_TIPO_USUARIO_ENCONTRADO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BuscarTipoUsuarioPorIdUseCaseTest {
    private AutoCloseable openMocks;

    private BuscarTipoUsuarioPorId useCase;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = org.mockito.MockitoAnnotations.openMocks(this);
        useCase = new BuscarTipoUsuarioPorIdUseCase(tipoUsuarioGatewayMock, new TipoUsuarioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarTipoUsuarioQuandoEncontrado() {
        // Arrange
        UUID id = UUID.randomUUID();
        TipoUsuario tipoUsuario = new TipoUsuario(id, "Admin", CategoriaUsuario.ADMIN);

        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.of(tipoUsuario));

        // Act
        BuscarTipoUsuarioOutput result = useCase.executar(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Admin", result.nome());
        assertEquals("ADMIN", result.categoriaUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(tipoUsuarioGatewayMock.buscaPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> useCase.executar(id));
        assertEquals(MSG_NENHUM_TIPO_USUARIO_ENCONTRADO + id, exception.getMessage());
    }
}
