package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.SenhaInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.adjt.chefmanagerapi.core.exceptions.SenhaInvalidaException.MSG_SENHA_INVALIDA_MINIMO_CARACTERES;
import static org.junit.jupiter.api.Assertions.*;

public class SenhaTest {
    @Test
    void deveCriarSenhaValida() {
        // Arrange & Act
        Senha senha = new Senha("senha123");

        // Assert
        assertNotNull(senha);
        assertEquals("senha123", senha.senha());
        assertEquals("senha123", senha.toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "12345",
            "abc",
            "a1b2",
            "pw",
            "x",
            " "
    })
    void deveLancarExcecaoQuandoSenhaMenorQue6CaracteresOuNulaOuVazia(String senhaInvalida) {
        // Assert e Act
        SenhaInvalidaException senhaInvalidaException = assertThrows(SenhaInvalidaException.class, () -> new Senha(senhaInvalida));
        assertEquals(MSG_SENHA_INVALIDA_MINIMO_CARACTERES, senhaInvalidaException.getMessage());
    }
}
