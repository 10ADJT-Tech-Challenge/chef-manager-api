package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Cliente;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.DonoRestaurante;
import com.adjt.chefmanagerapi.core.exceptions.*;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioHelper;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioHelper.*;
import static com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario.TipoUsuarioRequestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private CadastrarUsuario cadastrarUsuarioUseCase;

    @Mock
    private UsuarioGateway usuarioGatewayMock;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGatewayMock;

    @Mock
    private SenhaEncoderGateway senhaEncoderGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(
                usuarioGatewayMock,
                tipoUsuarioGatewayMock,
                senhaEncoderGatewayMock,
                new UsuarioMapperImpl()
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCriarUsuarioClienteComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo().id());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(Cliente.class));
    }

    @Test
    void deveCriarUsuarioDonoRestauranteComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido(UUID_TIPO_USUARIO_DONO_RESTAURANTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_DONO_RESTAURANTE))).thenReturn(Optional.of(TIPO_USUARIO_DONO));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(DonoRestaurante.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo().id());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(DonoRestaurante.class));
    }

    @Test
    void deveCriarUsuarioAdministradorComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido(UUID_TIPO_USUARIO_ADMIN);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_ADMIN))).thenReturn(Optional.of(TIPO_USUARIO_ADMIN));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(Administrador.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo().id());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(Administrador.class));
    }

    @Test
    void deveValidarEmailDuplicado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(true);

        // act & assert
        assertThrows(EmailJaCadastradoException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoMoreInteractions(usuarioGatewayMock);
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarEmailNullInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailNull(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        EmailObrigatorioException emailObrigatorioException = assertThrows(EmailObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        assertEquals("E-mail é obrigatório.", emailObrigatorioException.getMessage());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoInteractions(usuarioGatewayMock);
    }

    @Test
    void deveValidarEmailVazioInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailVazio(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        EmailObrigatorioException emailObrigatorioException = assertThrows(EmailObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));
        assertEquals("E-mail é obrigatório.", emailObrigatorioException.getMessage());

        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoInteractions(usuarioGatewayMock);
    }

    @Test
    void deveValidarLoginDuplicado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(true);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        LoginJaCadastradoException loginJaCadastradoException = assertThrows(LoginJaCadastradoException.class, () -> cadastrarUsuarioUseCase.executar(input));

        assertEquals("Login já cadastrado: " + input.login(), loginJaCadastradoException.getMessage());
        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verifyNoMoreInteractions(usuarioGatewayMock);

        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarLoginNullInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginNull(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        LoginObrigatorioException loginObrigatorioException = assertThrows(LoginObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        assertEquals("Login é obrigatório", loginObrigatorioException.getMessage());
        verifyNoInteractions(usuarioGatewayMock);
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarLoginVazioInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginVazio(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.of(TIPO_USUARIO_CLIENTE));
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        LoginObrigatorioException loginObrigatorioException = assertThrows(LoginObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));
        assertEquals("Login é obrigatório", loginObrigatorioException.getMessage());

        verifyNoInteractions(usuarioGatewayMock);
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarTipoUsuarioNaoEncontrado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginVazio(UUID_TIPO_USUARIO_CLIENTE);
        when(tipoUsuarioGatewayMock.buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE))).thenReturn(Optional.empty());
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cadastrarUsuarioUseCase.executar(input));
        assertEquals("Tipo de usuário inválido com id: " + UUID_TIPO_USUARIO_CLIENTE, exception.getMessage());

        verifyNoInteractions(usuarioGatewayMock);
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(tipoUsuarioGatewayMock).buscaPorId(UUID.fromString(UUID_TIPO_USUARIO_CLIENTE));
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaCurta() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaCurta(UUID_TIPO_USUARIO_CLIENTE);

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaNull() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaNull(UUID_TIPO_USUARIO_CLIENTE);

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }
}
