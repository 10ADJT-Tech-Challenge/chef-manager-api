package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenha;
import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenhaInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenhaUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlterarSenhaUseCaseTest {
    private AutoCloseable openMocks;

    private AlterarSenha alterarSenha;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private SenhaEncoderGateway senhaEncoderGateway;

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        alterarSenha = new AlterarSenhaUseCase(usuarioGateway, senhaEncoderGateway);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveAlterarSenhaComSucesso() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        String senhaAtual = "senha123";
        String novaSenha = "novaSenha123";
        String novaSenhaHash = "HASH_NOVA_SENHA_MOCK";
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(senhaEncoderGateway.verifica(senhaAtual, usuario.getSenha())).thenReturn(true);
        when(senhaEncoderGateway.encode(novaSenha)).thenReturn(novaSenhaHash);

        AlterarSenhaInput input = UsuarioHelper.AlterarSenhaHelper.criarInputAlteracaoSenha(usuario.getId());

        // act
        assertDoesNotThrow(() -> alterarSenha.executar(input));

        // assert
        verify(usuarioGateway).salvar(usuario);
        verify(senhaEncoderGateway).encode(novaSenha);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // arrange
        UUID usuarioId = UUID.randomUUID();
        when(usuarioGateway.buscarPorId(usuarioId)).thenReturn(Optional.empty());

        AlterarSenhaInput input = UsuarioHelper.AlterarSenhaHelper.criarInputAlteracaoSenha(usuarioId);
        // act & assert
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> alterarSenha.executar(input));
        assertEquals("Usuário não encontrado", noSuchElementException.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
        verify(usuarioGateway).buscarPorId(usuarioId);
        verifyNoInteractions(senhaEncoderGateway);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        String senhaAtualIncorreta = "senhaErrada";
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(senhaEncoderGateway.verifica(senhaAtualIncorreta, usuario.getSenha())).thenReturn(false);

        AlterarSenhaInput input = UsuarioHelper.AlterarSenhaHelper.criarInputAlteracaoSenhaIncorreta(usuario.getId());

        // act & assert
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> alterarSenha.executar(input));
        assertEquals("Senha atual incorreta", illegalArgumentException.getMessage());
        verify(usuarioGateway).buscarPorId(usuario.getId());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
        verify(senhaEncoderGateway).verifica(senhaAtualIncorreta, usuario.getSenha());
    }
}
