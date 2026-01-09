package com.adjt.chefmanagerapi.core.usecases.usuario.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Cliente;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.DonoRestaurante;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.exceptions.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.core.exceptions.LoginJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
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

import static com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioHelper.*;
import static com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario.TipoUsuarioRequestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private AtualizarUsuario atualizarUsuario;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        atualizarUsuario = new AtualizarUsuarioUseCase(
                usuarioGateway,
                tipoUsuarioGateway,
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
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), UUID_TIPO_USUARIO_ADMIN);
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_ADMIN))).thenReturn(Optional.of(TIPO_USUARIO_ADMIN));
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(any())).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(any())).thenReturn(Optional.empty());
        when(usuarioGateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertNotNull(outputAtualizacao);
        assertEquals(input.nome(), outputAtualizacao.nome());
        assertEquals(input.email(), outputAtualizacao.email());
        assertEquals(input.login(), outputAtualizacao.login());
        assertEquals(input.tipo(), outputAtualizacao.tipo().id());
        assertEquals(input.endereco().rua(), outputAtualizacao.endereco().rua());
        assertEquals(input.endereco().numero(), outputAtualizacao.endereco().numero());
        assertEquals(input.endereco().cidade(), outputAtualizacao.endereco().cidade());
        assertEquals(input.endereco().cep(), outputAtualizacao.endereco().cep());
        assertEquals(input.endereco().uf(), outputAtualizacao.endereco().uf());
        verify(usuarioGateway).salvar(any(Administrador.class));
    }

    @Test
    void deveAtualizaUsuarioDonoRestauranteComSucesso() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), UUID_TIPO_USUARIO_DONO_RESTAURANTE);
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_DONO_RESTAURANTE))).thenReturn(Optional.of(TIPO_USUARIO_DONO));
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(any())).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(any())).thenReturn(Optional.empty());
        when(usuarioGateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertNotNull(outputAtualizacao);
        assertEquals(input.nome(), outputAtualizacao.nome());
        assertEquals(input.email(), outputAtualizacao.email());
        assertEquals(input.login(), outputAtualizacao.login());
        assertEquals(input.tipo(), outputAtualizacao.tipo().id());
        assertEquals(input.endereco().rua(), outputAtualizacao.endereco().rua());
        assertEquals(input.endereco().numero(), outputAtualizacao.endereco().numero());
        assertEquals(input.endereco().cidade(), outputAtualizacao.endereco().cidade());
        assertEquals(input.endereco().cep(), outputAtualizacao.endereco().cep());
        assertEquals(input.endereco().uf(), outputAtualizacao.endereco().uf());
        verify(usuarioGateway).salvar(any(DonoRestaurante.class));
    }

    @Test
    void deveAtualizaUsuarioComSucessoSemAlterarEmailELogin() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoComMesmoEmailELogin(usuario.getId(), UUID.fromString(UUID_TIPO_USUARIO_ADMIN));
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_ADMIN))).thenReturn(Optional.of(TIPO_USUARIO_ADMIN));
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorLogin(input.login())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertNotNull(outputAtualizacao);
        assertEquals(input.nome(), outputAtualizacao.nome());
        assertEquals(input.email(), outputAtualizacao.email());
        assertEquals(input.login(), outputAtualizacao.login());
        assertEquals(input.tipo(), outputAtualizacao.tipo().id());
        assertEquals(input.endereco().rua(), outputAtualizacao.endereco().rua());
        assertEquals(input.endereco().numero(), outputAtualizacao.endereco().numero());
        assertEquals(input.endereco().cidade(), outputAtualizacao.endereco().cidade());
        assertEquals(input.endereco().cep(), outputAtualizacao.endereco().cep());
        assertEquals(input.endereco().uf(), outputAtualizacao.endereco().uf());
        verify(usuarioGateway).salvar(any(Administrador.class));
    }

    @Test
    void deveLancarErroQuandoUsuarioNaoEncontrado() {
        // arrange
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(UUID.randomUUID(), UUID_TIPO_USUARIO_ADMIN);
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(NoSuchElementException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveLancarErroQuandoEmailJaExistir() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        Usuario outroUsuario = UsuarioHelper.buscaUsuario("ADMIN");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), UUID_TIPO_USUARIO_ADMIN);
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_ADMIN))).thenReturn(Optional.of(TIPO_USUARIO_ADMIN));
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(outroUsuario));

        // act & assert
        assertThrows(EmailJaCadastradoException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveLancarErroQuandoLoginJaExistir() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        Usuario outroUsuario = UsuarioHelper.buscaUsuario("ADMIN");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), UUID_TIPO_USUARIO_ADMIN);
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_ADMIN))).thenReturn(Optional.of(TIPO_USUARIO_ADMIN));
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(input.email())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorLogin(input.login())).thenReturn(Optional.of(outroUsuario));

        // act & assert
        assertThrows(LoginJaCadastradoException.class, () -> atualizarUsuario.executar(input));
    }

    @Test
    void deveLancarErroQuandoTipoUsuarioNaoEncontrado() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoCompleta(usuario.getId(), UUID_TIPO_USUARIO_ADMIN);
        when(tipoUsuarioGateway.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorId(input.id())).thenReturn(Optional.of(usuario));

        // act & assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> atualizarUsuario.executar(input));
        assertEquals("Nenhum tipo de usuário encontrado com o id: " + UUID_TIPO_USUARIO_ADMIN, exception.getMessage());
        verify(usuarioGateway).buscarPorId(input.id());
        verify(usuarioGateway).buscarPorId(input.id());
    }

    @Test
    void deveAtualizarApenasOsCamposFornecidos() {
        // arrange
        Usuario usuario = UsuarioHelper.buscaUsuario("CLIENTE");
        AtualizarUsuarioInput input = UsuarioHelper.AtualizarUsuarioHelper.criarInputAtualizacaoApenasLogin(usuario.getId());
        when(usuarioGateway.buscarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioGateway.buscarPorEmail(any())).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(any())).thenReturn(Optional.empty());
        when(usuarioGateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        String nomeOriginal = usuario.getNome();
        String emailOriginal = usuario.getEmail().toString();
        String ruaOriginal = usuario.getEndereco().rua();
        String cidadeOriginal = usuario.getEndereco().cidade();

        // act
        AtualizarUsuarioOutput outputAtualizacao = atualizarUsuario.executar(input);

        // assert
        assertEquals(nomeOriginal, outputAtualizacao.nome());
        assertEquals(emailOriginal, outputAtualizacao.email());
        assertEquals(ruaOriginal, outputAtualizacao.endereco().rua());
        assertEquals(cidadeOriginal, outputAtualizacao.endereco().cidade());
        assertEquals("novo.login", outputAtualizacao.login());
        verify(usuarioGateway).salvar(any(Cliente.class));
    }
}
