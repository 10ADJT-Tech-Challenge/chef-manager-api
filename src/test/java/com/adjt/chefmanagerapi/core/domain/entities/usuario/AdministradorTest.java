
package com.adjt.chefmanagerapi.core.domain.entities.usuario;

import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
import com.adjt.chefmanagerapi.core.exceptions.LoginObrigatorioException;
import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
import com.adjt.chefmanagerapi.core.exceptions.SenhaObrigatoriaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.adjt.chefmanagerapi.core.exceptions.LoginObrigatorioException.MSG_LOGIN_OBRIGATORIO;
import static com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException.MSG_NOME_OBRIGATORIO;
import static com.adjt.chefmanagerapi.core.exceptions.SenhaObrigatoriaException.MSG_NOVA_SENHA_OBRIGATORIA;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    private static final String NOME = "Admin Teste";
    private static final String EMAIL = "admin@teste.com";
    private static final String LOGIN = "admin.teste";
    private static final String SENHA = "senha123";
    private static final Endereco ENDERECO = new Endereco(
            "Rua Teste",
            "123",
            "Cidade Teste",
            "12345-678",
            "UF"
    );

    @Test
    void deveCriarAdministradorComSucesso() {
        // arrange & act
        Usuario admin = new Administrador(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(admin);
        assertNotNull(admin.getId());
        assertEquals(NOME, admin.getNome());
        assertEquals(EMAIL, admin.getEmail().toString());
        assertEquals(LOGIN, admin.getLogin());
        assertEquals(SENHA, admin.getSenha());
        assertEquals(ENDERECO, admin.getEndereco());
        assertNull(admin.getDataUltimaAlteracao());
    }

    @Test
    void deveCriarAdministradorComIdEspecificoComSucesso() {
        // arrange
        UUID id = UUID.randomUUID();

        // act
        Usuario admin = new Administrador(id, NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(admin);
        assertEquals(id, admin.getId());
        assertEquals(NOME, admin.getNome());
        assertEquals(EMAIL, admin.getEmail().toString());
        assertEquals(LOGIN, admin.getLogin());
        assertEquals(SENHA, admin.getSenha());
        assertEquals(ENDERECO, admin.getEndereco());
        assertNull(admin.getDataUltimaAlteracao());
    }

    @Test
    void deveRetornarTipoUsuarioAdmin() {
        // arrange
        Usuario admin = new Administrador(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // act & assert
        assertEquals(TipoUsuario.ADMIN, admin.getTipo());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarNomeObrigatorio(String nomeInvalido) {
        // act & assert
        NomeObrigatorioException nomeObrigatorio = assertThrows(NomeObrigatorioException.class,
                () -> new Administrador(nomeInvalido, EMAIL, LOGIN, SENHA, ENDERECO));
        assertEquals(MSG_NOME_OBRIGATORIO, nomeObrigatorio.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarLoginObrigatorio(String loginInvalido) {
        // act & assert
        LoginObrigatorioException loginObrigatorio = assertThrows(LoginObrigatorioException.class,
                () -> new Administrador(NOME, EMAIL, loginInvalido, SENHA, ENDERECO));
        assertEquals(MSG_LOGIN_OBRIGATORIO, loginObrigatorio.getMessage());

    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarSenhaObrigatoria(String senhaInvalida) {
        // act & assert
        SenhaObrigatoriaException senhaObrigatoria = assertThrows(SenhaObrigatoriaException.class,
                () -> new Administrador(NOME, EMAIL, LOGIN, senhaInvalida, ENDERECO));
        assertEquals(MSG_NOVA_SENHA_OBRIGATORIA, senhaObrigatoria.getMessage());
    }

    @Test
    void deveAtualizarInformacoesComSucesso() {
        // arrange
        Usuario admin = new Administrador(NOME, EMAIL, LOGIN, SENHA, ENDERECO);
        String novoNome = "Novo Nome";
        String novoEmail = "novo@email.com";
        String novoLogin = "novo.login";
        String novaSenha = "nova.senha";
        Endereco novoEndereco = new Endereco("Nova Rua", "456", "Nova Cidade", "87654-321", "UF");

        // act && assert
        assertNull(admin.getDataUltimaAlteracao());

        admin.atualizarNome(novoNome);
        assertEquals(novoNome, admin.getNome());
        assertNotNull(admin.getDataUltimaAlteracao());
        OffsetDateTime dataAlteracao = admin.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    admin.atualizarEmail(novoEmail);
                    return true;
                });

        assertEquals(novoEmail, admin.getEmail().toString());
        assertNotNull(admin.getDataUltimaAlteracao());
        assertTrue(admin.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = admin.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    admin.atualizarLogin(novoLogin);
                    return true;
                });

        assertEquals(novoLogin, admin.getLogin());
        assertNotNull(admin.getDataUltimaAlteracao());
        assertTrue(admin.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = admin.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    admin.alterarSenha(novaSenha);
                    return true;
                });

        assertEquals(novaSenha, admin.getSenha());
        assertNotNull(admin.getDataUltimaAlteracao());
        assertTrue(admin.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = admin.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    admin.atualizarEndereco(novoEndereco);
                    return true;
                });

        assertEquals(novoEndereco, admin.getEndereco());
        assertNotNull(admin.getDataUltimaAlteracao());
        assertTrue(admin.getDataUltimaAlteracao().isAfter(dataAlteracao));
    }
}