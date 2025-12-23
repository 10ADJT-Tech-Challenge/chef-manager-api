package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapperImpl;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioUseCase;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class AtualizarUsuarioUseCaseTest {

    private AutoCloseable openMocks;

    private AtualizarUsuario atualizarUsuario;

    @Mock
    private UsuarioGateway usuarioGatewayRepositoryMock;

    @Mock
    private SenhaEncoderGateway senhaEncoderGatewayMock;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        atualizarUsuario = new AtualizarUsuarioUseCase(
                usuarioGatewayRepositoryMock,
                new UsuarioMapperImpl()
        ) {
        };
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

}
