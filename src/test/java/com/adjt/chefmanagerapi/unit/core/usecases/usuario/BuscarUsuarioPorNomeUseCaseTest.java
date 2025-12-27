package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioPorNome;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioPorNomeUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscarUsuarioPorNomeUseCaseTest {
    private AutoCloseable openMocks;

    private BuscarUsuarioPorNome buscarUsuarioPorNome;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        buscarUsuarioPorNome = new BuscarUsuarioPorNomeUseCase(usuarioGateway, new UsuarioMapperImpl());
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        when(usuarioGateway.buscarPorNome(usuario.getNome())).thenReturn(Optional.of(usuario));
        // act
        BuscarUsuarioOutput output = buscarUsuarioPorNome.executar(usuario.getNome());

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

        verify(usuarioGateway).buscarPorNome(usuario.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        // arrange
        when(usuarioGateway.buscarPorNome(any())).thenReturn(Optional.empty());
        String nome = "nome";

        // act e assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class,
                () -> buscarUsuarioPorNome.executar(nome));

        assertEquals("Nenhum usuário encontrado com esse nome: " + nome,
                noSuchElementException.getMessage());
        verify(usuarioGateway).buscarPorNome(nome);
    }
}
