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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastrarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private CadastrarUsuario cadastrarUsuarioUseCase;

    @Mock
    private UsuarioGateway usuarioGatewayRepositoryMock;

    @Mock
    private SenhaEncoderGateway senhaEncoderGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(
                usuarioGatewayRepositoryMock,
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
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayRepositoryMock).salvar(any(Usuario.class));
    }

    @Test
    void deveCriarUsuarioDonoRestauranteComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("DONO_RESTAURANTE");
        Usuario usuarioSimulado = UsuarioHelper.criarUsuarioSimulado(input);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayRepositoryMock).salvar(any(Usuario.class));
    }

    @Test
    void deveCriarUsuarioAdministradorComDadosValidos() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("ADMIN");
        Usuario usuarioSimulado = UsuarioHelper.criarUsuarioSimulado(input);
        when(senhaEncoderGatewayMock.encode(input.senha())).thenReturn("HASH_SENHA_MOCK");
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login())).thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.salvar(any(Usuario.class))).thenReturn(usuarioSimulado);

        // act
        var output = cadastrarUsuarioUseCase.executar(input);

        // assert
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(input.nome(), output.nome());
        assertEquals(input.email(), output.email());
        assertEquals(input.login(), output.login());
        assertEquals(input.tipo(), output.tipo());

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verify(senhaEncoderGatewayMock).encode(input.senha());
        verify(usuarioGatewayRepositoryMock).salvar(any(Usuario.class));
    }

    @Test
    void deveValidarEmailDuplicado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("CLIENTE");
        Usuario usuarioExistente = UsuarioHelper.criarUsuarioSimulado(input);
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email()))
                .thenReturn(Optional.of(usuarioExistente));

        // act & assert
        assertThrows(EmailJaCadastradoException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarEmailNullInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailNull("CLIENTE");

        // act & assert
        assertThrows(EmailObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayRepositoryMock, senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarEmailVazioInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComEmailVazio("CLIENTE");

        // act & assert
        assertThrows(EmailObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verifyNoInteractions(usuarioGatewayRepositoryMock, senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarLoginDuplicado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputValido("CLIENTE");
        Usuario usuarioExistente = UsuarioHelper.criarUsuarioSimulado(input);
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email()))
                .thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login()))
                .thenReturn(Optional.of(usuarioExistente));

        // act & assert
        assertThrows(LoginJaCadastradoException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarLoginNullInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginNull("CLIENTE");

        // act & assert
        assertThrows(LoginObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarLoginVazioInformado() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComLoginVazio("CLIENTE");

        // act & assert
        assertThrows(LoginObrigatorioException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaCurta() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaCurta("CLIENTE");
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email()))
                .thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login()))
                .thenReturn(Optional.empty());

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }

    @Test
    void deveValidarSenhaNull() {
        // arrange
        CadastrarUsuarioInput input = UsuarioHelper.CadastrarUsuarioHelper.criarInputComSenhaNull("CLIENTE");
        when(usuarioGatewayRepositoryMock.buscarPorEmail(input.email()))
                .thenReturn(Optional.empty());
        when(usuarioGatewayRepositoryMock.buscarPorLogin(input.login()))
                .thenReturn(Optional.empty());

        // act & assert
        assertThrows(SenhaInvalidaException.class, () -> cadastrarUsuarioUseCase.executar(input));

        verify(usuarioGatewayRepositoryMock).buscarPorEmail(input.email());
        verify(usuarioGatewayRepositoryMock).buscarPorLogin(input.login());
        verifyNoMoreInteractions(usuarioGatewayRepositoryMock);
        verifyNoInteractions(senhaEncoderGatewayMock);
    }
}
