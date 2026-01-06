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

class DonoRestauranteTest {

    private static final String NOME = "Restaurante Teste";
    private static final String EMAIL = "restaurante@teste.com";
    private static final String LOGIN = "restaurante.teste";
    private static final String SENHA = "senha123";
    private static final Endereco ENDERECO = new Endereco(
            "Rua Teste",
            "123",
            "Cidade Teste",
            "12345-678",
            "UF"
    );

    @Test
    void deveCriarDonoRestauranteComSucesso() {
        // arrange & act
        Usuario donoRestaurante = new DonoRestaurante(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(donoRestaurante);
        assertNotNull(donoRestaurante.getId());
        assertEquals(NOME, donoRestaurante.getNome());
        assertEquals(EMAIL, donoRestaurante.getEmail().toString());
        assertEquals(LOGIN, donoRestaurante.getLogin());
        assertEquals(SENHA, donoRestaurante.getSenha());
        assertEquals(ENDERECO, donoRestaurante.getEndereco());
        assertNull(donoRestaurante.getDataUltimaAlteracao());
    }

    @Test
    void deveCriarDonoRestauranteComIdEspecificoComSucesso() {
        // arrange
        UUID id = UUID.randomUUID();

        // act
        Usuario donoRestaurante = new DonoRestaurante(id, NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(donoRestaurante);
        assertEquals(id, donoRestaurante.getId());
        assertEquals(NOME, donoRestaurante.getNome());
        assertEquals(EMAIL, donoRestaurante.getEmail().toString());
        assertEquals(LOGIN, donoRestaurante.getLogin());
        assertEquals(SENHA, donoRestaurante.getSenha());
        assertEquals(ENDERECO, donoRestaurante.getEndereco());
        assertNull(donoRestaurante.getDataUltimaAlteracao());
    }

    @Test
    void deveRetornarTipoUsuarioDonoRestaurante() {
        // arrange
        Usuario donoRestaurante = new DonoRestaurante(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // act & assert
        assertEquals(TipoUsuario.DONO_RESTAURANTE, donoRestaurante.getTipo());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarNomeObrigatorio(String nomeInvalido) {
        // act & assert
        NomeObrigatorioException nomeObrigatorio = assertThrows(NomeObrigatorioException.class,
                () -> new DonoRestaurante(nomeInvalido, EMAIL, LOGIN, SENHA, ENDERECO));
        assertEquals(MSG_NOME_OBRIGATORIO, nomeObrigatorio.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarLoginObrigatorio(String loginInvalido) {
        // act & assert
        LoginObrigatorioException loginObrigatorio = assertThrows(LoginObrigatorioException.class,
                () -> new DonoRestaurante(NOME, EMAIL, loginInvalido, SENHA, ENDERECO));
        assertEquals(MSG_LOGIN_OBRIGATORIO, loginObrigatorio.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarSenhaObrigatoria(String senhaInvalida) {
        // act & assert
        SenhaObrigatoriaException senhaObrigatoria = assertThrows(SenhaObrigatoriaException.class,
                () -> new DonoRestaurante(NOME, EMAIL, LOGIN, senhaInvalida, ENDERECO));
        assertEquals(MSG_NOVA_SENHA_OBRIGATORIA, senhaObrigatoria.getMessage());
    }

    @Test
    void deveAtualizarInformacoesComSucesso() {
        // arrange
        Usuario donoRestaurante = new DonoRestaurante(NOME, EMAIL, LOGIN, SENHA, ENDERECO);
        String novoNome = "Novo Nome";
        String novoEmail = "novo@email.com";
        String novoLogin = "novo.login";
        String novaSenha = "nova.senha";
        Endereco novoEndereco = new Endereco("Nova Rua", "456", "Nova Cidade", "87654-321", "UF");

        // act && assert
        assertNull(donoRestaurante.getDataUltimaAlteracao());

        donoRestaurante.atualizarNome(novoNome);
        assertEquals(novoNome, donoRestaurante.getNome());
        assertNotNull(donoRestaurante.getDataUltimaAlteracao());
        OffsetDateTime dataAlteracao = donoRestaurante.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    donoRestaurante.atualizarEmail(novoEmail);
                    return true;
                });

        assertEquals(novoEmail, donoRestaurante.getEmail().toString());
        assertNotNull(donoRestaurante.getDataUltimaAlteracao());
        assertTrue(donoRestaurante.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = donoRestaurante.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    donoRestaurante.atualizarLogin(novoLogin);
                    return true;
                });

        assertEquals(novoLogin, donoRestaurante.getLogin());
        assertNotNull(donoRestaurante.getDataUltimaAlteracao());
        assertTrue(donoRestaurante.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = donoRestaurante.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    donoRestaurante.alterarSenha(novaSenha);
                    return true;
                });

        assertEquals(novaSenha, donoRestaurante.getSenha());
        assertNotNull(donoRestaurante.getDataUltimaAlteracao());
        assertTrue(donoRestaurante.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = donoRestaurante.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    donoRestaurante.atualizarEndereco(novoEndereco);
                    return true;
                });

        assertEquals(novoEndereco, donoRestaurante.getEndereco());
        assertNotNull(donoRestaurante.getDataUltimaAlteracao());
        assertTrue(donoRestaurante.getDataUltimaAlteracao().isAfter(dataAlteracao));
    }
}