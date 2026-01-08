package com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarTodosTiposUsuarioUseCaseTest {
    private AutoCloseable openMocks;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private BuscarTodosTiposUsuarioUseCase useCase;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarTodosTiposUsuarioUseCase(tipoUsuarioGateway, new TipoUsuarioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve retornar lista de tipos de usuário quando executado com sucesso")
    void deveRetornarListaTiposUsuario() {
        // Arrange
        TipoUsuario tipoUsuario1 = new TipoUsuario(UUID.randomUUID(), "Admin", CategoriaUsuario.ADMIN);
        TipoUsuario tipoUsuario2 = new TipoUsuario(UUID.randomUUID(), "Cliente", CategoriaUsuario.CLIENTE);
        List<TipoUsuario> tiposUsuario = List.of(tipoUsuario1, tipoUsuario2);

        when(tipoUsuarioGateway.buscarTodos()).thenReturn(tiposUsuario);

        // Act
        List<BuscarTipoUsuarioOutput> resultado = StreamSupport.stream(useCase.executar(null).spliterator(), false).toList();

        // Assert
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(t -> t.id().equals(tipoUsuario1.getId())));
        assertTrue(resultado.stream().anyMatch(t -> t.id().equals(tipoUsuario2.getId())));

        verify(tipoUsuarioGateway).buscarTodos();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver tipos de usuário cadastrados")
    void deveRetornarListaVazia() {
        // Arrange
        when(tipoUsuarioGateway.buscarTodos()).thenReturn(List.of());

        // Act
        List<BuscarTipoUsuarioOutput> resultado = StreamSupport.stream(useCase.executar(null).spliterator(), false).toList();

        // Assert
        assertTrue(resultado.isEmpty());

        verify(tipoUsuarioGateway).buscarTodos();
    }
}