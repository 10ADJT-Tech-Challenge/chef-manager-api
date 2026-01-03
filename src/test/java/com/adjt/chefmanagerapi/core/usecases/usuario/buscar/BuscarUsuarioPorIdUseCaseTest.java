package com.adjt.chefmanagerapi.core.usecases.usuario.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioHelper;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
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

class BuscarUsuarioPorIdUseCaseTest {
    private AutoCloseable openMocks;

    private BuscarUsuarioPorId buscarUsuarioPorId;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        buscarUsuarioPorId = new BuscarUsuarioPorIdUseCase(usuarioGateway, new UsuarioMapperImpl());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        // act
        BuscarUsuarioOutput output = buscarUsuarioPorId.executar(usuario.getId());

        // assert
        assertNotNull(output);
        assertEquals(usuario.getId(), output.id());
        assertEquals(usuario.getNome(), output.nome());
        assertEquals(usuario.getEmail().toString(), output.email());
        assertEquals(usuario.getLogin(), output.login());
        assertEquals(usuario.getTipo().name(), output.tipo());

        assertNotNull(output.endereco());
        assertEquals(usuario.getEndereco().rua(), output.endereco().rua());
        assertEquals(usuario.getEndereco().numero(), output.endereco().numero());
        assertEquals(usuario.getEndereco().cidade(), output.endereco().cidade());
        assertEquals(usuario.getEndereco().cep(), output.endereco().cep());
        assertEquals(usuario.getEndereco().uf(), output.endereco().uf());

        verify(usuarioGateway).buscarPorId(usuario.getId());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        // arrange
        when(usuarioGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.empty());
        UUID input = UUID.randomUUID();

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> buscarUsuarioPorId.executar(input));

        assertEquals("Nenhum usuário encontrado com o id: " + input,
                noSuchElementException.getMessage());
        verify(usuarioGateway).buscarPorId(input);
    }
}
