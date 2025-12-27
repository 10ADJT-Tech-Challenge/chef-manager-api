package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.exceptions.*;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastrarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private CadastrarUsuario cadastrarUsuarioUseCase;

    @Mock
    private UsuarioGateway usuarioGatewayMock;

    @Mock
    private SenhaEncoderGateway senhaEncoderGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(
                usuarioGatewayMock,
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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("CLIENTE");
        Usuario usuarioSimulado = UsuarioHelper.criarUsuarioSimulado(input);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(Usuario.class));
    }

    @Test
    void deveCriarUsuarioDonoRestauranteComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("DONO_RESTAURANTE");
        Usuario usuarioSimulado = UsuarioHelper.criarUsuarioSimulado(input);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(Usuario.class));
    }

    @Test
    void deveCriarUsuarioAdministradorComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("ADMIN");
        Usuario usuarioSimulado = UsuarioHelper.criarUsuarioSimulado(input);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(false);
        when(usuarioGatewayMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayMock).existePorEmail(input.email());
        verify(usuarioGatewayMock).existePorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayMock).salvar(any(Usuario.class));
    }

    @Test
    void deveValidarEmailDuplicado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("CLIENTE");
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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailNull("CLIENTE");
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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailVazio("CLIENTE");
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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("CLIENTE");
        when(usuarioGatewayMock.existePorEmail(input.email())).thenReturn(false);
        when(usuarioGatewayMock.existePorLogin(input.login())).thenReturn(true);

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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginNull("CLIENTE");
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
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginVazio("CLIENTE");
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");

        // act & assert
        LoginObrigatorioException loginObrigatorioException = assertThrows(LoginObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));
        assertEquals("Login é obrigatório", loginObrigatorioException.getMessage());

        verifyNoInteractions(usuarioGatewayMock);
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verifyNoMoreInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaCurta() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaCurta("CLIENTE");

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaNull() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaNull("CLIENTE");

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }
}
