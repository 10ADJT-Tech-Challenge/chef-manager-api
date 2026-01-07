package com.adjt.chefmanagerapi.core.domain.entities.usuario;

import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
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

class ClienteTest {

    private static final String NOME = "Cliente Teste";
    private static final String EMAIL = "cliente@teste.com";
    private static final String LOGIN = "cliente.teste";
    private static final String SENHA = "senha123";
    private static final Endereco ENDERECO = new Endereco(
            "Rua Teste",
            "123",
            "Cidade Teste",
            "12345-678",
            "UF"
    );

    @Test
    void deveCriarClienteComSucesso() {
        // arrange & act
        Usuario cliente = new Cliente(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(cliente);
        assertNotNull(cliente.getId());
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail().toString());
        assertEquals(LOGIN, cliente.getLogin());
        assertEquals(SENHA, cliente.getSenha());
        assertEquals(ENDERECO, cliente.getEndereco());
        assertNull(cliente.getDataUltimaAlteracao());
    }

    @Test
    void deveCriarClienteComIdEspecificoComSucesso() {
        // arrange
        UUID id = UUID.randomUUID();

        // act
        Usuario cliente = new Cliente(id, NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // assert
        assertNotNull(cliente);
        assertEquals(id, cliente.getId());
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail().toString());
        assertEquals(LOGIN, cliente.getLogin());
        assertEquals(SENHA, cliente.getSenha());
        assertEquals(ENDERECO, cliente.getEndereco());
        assertNull(cliente.getDataUltimaAlteracao());
    }

    @Test
    void deveRetornarTipoUsuarioCliente() {
        // arrange
        Usuario cliente = new Cliente(NOME, EMAIL, LOGIN, SENHA, ENDERECO);

        // act & assert
        assertEquals(CategoriaUsuario.CLIENTE, cliente.getTipo());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarNomeObrigatorio(String nomeInvalido) {
        // act & assert
        NomeObrigatorioException nomeObrigatorio = assertThrows(NomeObrigatorioException.class,
                () -> new Cliente(nomeInvalido, EMAIL, LOGIN, SENHA, ENDERECO));
        assertEquals(MSG_NOME_OBRIGATORIO, nomeObrigatorio.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarLoginObrigatorio(String loginInvalido) {
        // act & assert
        LoginObrigatorioException loginObrigatorio = assertThrows(LoginObrigatorioException.class,
                () -> new Cliente(NOME, EMAIL, loginInvalido, SENHA, ENDERECO));
        assertEquals(MSG_LOGIN_OBRIGATORIO, loginObrigatorio.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveValidarSenhaObrigatoria(String senhaInvalida) {
        // act & assert
        SenhaObrigatoriaException senhaObrigatoria = assertThrows(SenhaObrigatoriaException.class,
                () -> new Cliente(NOME, EMAIL, LOGIN, senhaInvalida, ENDERECO));
        assertEquals(MSG_NOVA_SENHA_OBRIGATORIA, senhaObrigatoria.getMessage());
    }

    @Test
    void deveAtualizarInformacoesComSucesso() {
        // arrange
        Usuario cliente = new Cliente(NOME, EMAIL, LOGIN, SENHA, ENDERECO);
        String novoNome = "Novo Nome";
        String novoEmail = "novo@email.com";
        String novoLogin = "novo.login";
        String novaSenha = "nova.senha";
        Endereco novoEndereco = new Endereco("Nova Rua", "456", "Nova Cidade", "87654-321", "UF");

        // act && assert
        assertNull(cliente.getDataUltimaAlteracao());

        cliente.atualizarNome(novoNome);
        assertEquals(novoNome, cliente.getNome());
        assertNotNull(cliente.getDataUltimaAlteracao());
        OffsetDateTime dataAlteracao = cliente.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    cliente.atualizarEmail(novoEmail);
                    return true;
                });

        assertEquals(novoEmail, cliente.getEmail().toString());
        assertNotNull(cliente.getDataUltimaAlteracao());
        assertTrue(cliente.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = cliente.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    cliente.atualizarLogin(novoLogin);
                    return true;
                });

        assertEquals(novoLogin, cliente.getLogin());
        assertNotNull(cliente.getDataUltimaAlteracao());
        assertTrue(cliente.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = cliente.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    cliente.alterarSenha(novaSenha);
                    return true;
                });

        assertEquals(novaSenha, cliente.getSenha());
        assertNotNull(cliente.getDataUltimaAlteracao());
        assertTrue(cliente.getDataUltimaAlteracao().isAfter(dataAlteracao));
        dataAlteracao = cliente.getDataUltimaAlteracao();

        await().pollInterval(Duration.ofMillis(1))
                .atMost(Duration.ofMillis(10))
                .until(() -> {
                    cliente.atualizarEndereco(novoEndereco);
                    return true;
                });

        assertEquals(novoEndereco, cliente.getEndereco());
        assertNotNull(cliente.getDataUltimaAlteracao());
        assertTrue(cliente.getDataUltimaAlteracao().isAfter(dataAlteracao));
    }
}