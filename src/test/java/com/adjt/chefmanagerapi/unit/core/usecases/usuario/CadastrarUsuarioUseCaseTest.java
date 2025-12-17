package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Fail.fail;

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
        fail("deveCriarUsuarioComDadosValidos não implementado");
    }

    @Test
    void deveCriarUsuarioDonoRestauranteComDadosValidos() {
        fail("deveCriarUsuarioDonoRestauranteComDadosValidos não implementado");
    }

    @Test
    void deveCriarUsuarioAdministradorComDadosValidos() {
        fail("deveCriarUsuarioDonoRestauranteComDadosValidos não implementado");
    }

    @Test
    void deveValidarEmailDuplicado() {
        fail("deveValidarEmailDuplicado não implementado");
    }

    @Test
    void deveValidarEmailNaoInformado() {
        fail("deveValidarEmailDuplicado não implementado");
    }

    @Test
    void deveValidarLoginDuplicado() {
        fail("deveValidarLoginDuplicado não implementado");
    }

    @Test
    void deveValidarLoginNaoInformado() {
        fail("deveValidarLoginNaoInformado não implementado");
    }

    @Test
    void deveValidarSenhaCurta() {
        fail("deveValidarLoginNaoInformado não implementado");
    }
}
