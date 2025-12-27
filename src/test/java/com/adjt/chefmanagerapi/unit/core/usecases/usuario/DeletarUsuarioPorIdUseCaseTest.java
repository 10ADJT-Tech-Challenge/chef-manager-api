package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.deletar.DeletarUsuarioPorId;
import com.adjt.chefmanagerapi.core.usecases.usuario.deletar.DeletarUsuarioPorIdUseCase;
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

public class DeletarUsuarioPorIdUseCaseTest {

    private AutoCloseable openMocks;

    private DeletarUsuarioPorId deletarUsuarioPorId;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        deletarUsuarioPorId = new DeletarUsuarioPorIdUseCase(usuarioGateway);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveDeletarUsuarioPorIdComSucesso() {
        // arrange
        when(usuarioGateway.existePorId(any(UUID.class))).thenReturn(true);
        UUID id = UUID.randomUUID();

        // act
        deletarUsuarioPorId.executar(id);

        // assert
        verify(usuarioGateway).existePorId(id);
        verify(usuarioGateway).deletarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        // arrange
        when(usuarioGateway.existePorId(any(UUID.class))).thenReturn(false);
        UUID id = UUID.randomUUID();

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> deletarUsuarioPorId.executar(id));

        assertEquals("Nenhum usuário encontrado com o id: " + id, noSuchElementException.getMessage());
        verify(usuarioGateway).existePorId(id);
        verify(usuarioGateway, never()).deletarPorId(any(UUID.class));
    }
}
