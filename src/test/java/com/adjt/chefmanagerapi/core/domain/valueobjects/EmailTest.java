package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EmailInvalidoException;
import com.adjt.chefmanagerapi.core.exceptions.EmailObrigatorioException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.adjt.chefmanagerapi.core.exceptions.EmailInvalidoException.MSG_FORMATO_DE_EMAIL_INVALIDO_EXEMPLO_CORRETO;
import static com.adjt.chefmanagerapi.core.exceptions.EmailObrigatorioException.MSG_EMAIL_E_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "usuario@dominio.com",
            "usuario_1@dominio.com",
            "user.name@dominio.com.br",
            "user+tag@dominio.com",
            "usuario123@dominio.com",
            "user-name@dominio.com"
    })
    void deveCriarEmailValidoComDiferentesFormatos(String enderecoEmail) {
        // act
        Email email = new Email(enderecoEmail);

        // assert
        assertEquals(enderecoEmail, email.email());
        assertEquals(enderecoEmail, email.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "usuariodominio.com",       // sem @
            "usuario@",                 // sem domínio
            "@dominio.com",             // sem usuário
            "usuario@dominio",          // sem TLD (Domínio de Nível Superior)
            "usuario@dominio..com",     // domínio com ponto duplo
            "usuario@dominio.c",        // TLD muito curto
            "usuario@.com",             // domínio inválido
            "usuario@dominio.",         // TLD vazio
            "usuario space@dominio.com" // com espaço
    })
    void deveLancarExcecaoParaEmailsInvalidos(String enderecoEmail) {
        // act & assert
        EmailInvalidoException exception = assertThrows(EmailInvalidoException.class,
                () -> new Email(enderecoEmail));
        assertEquals(MSG_FORMATO_DE_EMAIL_INVALIDO_EXEMPLO_CORRETO, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "   "
    })
    void deveLancarExcecaoParaEmailObrigatorio(String enderecoEmail) {
        // act & assert
        EmailObrigatorioException exception = assertThrows(EmailObrigatorioException.class,
                () -> new Email(enderecoEmail));
        assertEquals(MSG_EMAIL_E_OBRIGATORIO, exception.getMessage());
    }
}
