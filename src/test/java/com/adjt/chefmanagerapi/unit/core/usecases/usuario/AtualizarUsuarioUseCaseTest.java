package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.exceptions.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.core.exceptions.LoginJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioUseCase;
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

public class AtualizarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private AtualizarUsuario atualizarUsuario;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        atualizarUsuario = new AtualizarUsuarioUseCase(
                usuarioGateway,
                new UsuarioMapperImpl()
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveAtualizaUsuarioComSucesso() {
        // arrange
        Usuario usuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), "ADMIN");
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(any())).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(any())).thenReturn(Optional.empty());

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertNotNull(outputAtualizacao);
        assertEquals(input.nome(), outputAtualizacao.nome());
        assertEquals(input.email(), outputAtualizacao.email());
        assertEquals(input.login(), outputAtualizacao.login());
        assertEquals(input.tipo(), outputAtualizacao.tipo());
        assertEquals(input.endereco().rua(), outputAtualizacao.endereco().rua());
        assertEquals(input.endereco().numero(), outputAtualizacao.endereco().numero());
        assertEquals(input.endereco().cidade(), outputAtualizacao.endereco().cidade());
        assertEquals(input.endereco().cep(), outputAtualizacao.endereco().cep());
        assertEquals(input.endereco().uf(), outputAtualizacao.endereco().uf());
        verify(usuarioGateway).salvar(any(Usuario.class));
    }

    @Test
    void deveAtualizaUsuarioComSucessoSemAlterarEmailELogin() {
        // arrange
        Usuario usuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoComMesmoEmailELogin(usuario.getId(), "ADMIN");
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorLogin(input.login())).thenReturn(Optional.of(usuario));

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertNotNull(outputAtualizacao);
        assertEquals(input.nome(), outputAtualizacao.nome());
        assertEquals(input.email(), outputAtualizacao.email());
        assertEquals(input.login(), outputAtualizacao.login());
        assertEquals(input.tipo(), outputAtualizacao.tipo());
        assertEquals(input.endereco().rua(), outputAtualizacao.endereco().rua());
        assertEquals(input.endereco().numero(), outputAtualizacao.endereco().numero());
        assertEquals(input.endereco().cidade(), outputAtualizacao.endereco().cidade());
        assertEquals(input.endereco().cep(), outputAtualizacao.endereco().cep());
        assertEquals(input.endereco().uf(), outputAtualizacao.endereco().uf());
        verify(usuarioGateway).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarErroQuandoUsuarioNaoEncontrado() {
        // arrange
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(UUID.randomUUID(), "ADMIN");
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(NoSuchElementException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveLancarErroQuandoEmailJaExistir() {
        // arrange
        Usuario usuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("CLIENTE");
        Usuario outroUsuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("ADMIN");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), "ADMIN");
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(outroUsuario));

        // act & assert
        assertThrows(EmailJaCadastradoException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveLancarErroQuandoLoginJaExistir() {
        // arrange
        Usuario usuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("CLIENTE");
        Usuario outroUsuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("ADMIN");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), "ADMIN");
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorLogin(input.login())).thenReturn(Optional.of(outroUsuario));

        // act & assert
        assertThrows(LoginJaCadastradoException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveAtualizarApenasOsCamposFornecidos() {
        // arrange
        Usuario usuario = UsuarioHelper.AtualizarUsuarioHelper.buscaUsuarioParaAtualizar("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoApenasLogin(usuario.getId());
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(any())).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(any())).thenReturn(Optional.empty());

        String nomeOriginal = usuario.getNome();
        String emailOriginal = usuario.getEmail();
        String ruaOriginal = usuario.getEndereco().getRua();
        String cidadeOriginal = usuario.getEndereco().getCidade();

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertEquals(nomeOriginal, outputAtualizacao.nome());
        assertEquals(emailOriginal, outputAtualizacao.email());
        assertEquals(ruaOriginal, outputAtualizacao.endereco().rua());
        assertEquals(cidadeOriginal, outputAtualizacao.endereco().cidade());
        assertEquals("novo.login", outputAtualizacao.login());
        verify(usuarioGateway).salvar(any(Usuario.class));
    }
}
