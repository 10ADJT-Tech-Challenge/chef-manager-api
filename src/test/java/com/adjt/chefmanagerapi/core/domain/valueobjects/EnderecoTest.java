
package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EnderecoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoComDadosValidos() {
        // arrange
        String rua = "Rua das Flores";
        String numero = "123";
        String cidade = "São Paulo";
        String cep = "12345-678";
        String uf = "SP";

        // act
        Endereco endereco = new Endereco(rua, numero, cidade, cep, uf);

        // assert
        assertEquals(rua, endereco.rua());
        assertEquals(numero, endereco.numero());
        assertEquals(cidade, endereco.cidade());
        assertEquals(cep, endereco.cep());
        assertEquals("SP", endereco.uf());
    }

    @Test
    void deveNormalizarCepSemHifen() {
        // arrange
        String cep = "12345678";

        // act
        Endereco endereco = new Endereco("Rua Teste", "123", "Cidade Teste", cep, "SP");

        // assert
        assertEquals("12345-678", endereco.cep());
    }

    @Test
    void deveConverterUfParaMaiusculo() {
        // arrange
        String uf = "sp";

        // act
        Endereco endereco = new Endereco("Rua Teste", "123", "Cidade Teste", "12345-678", uf);

        // assert
        assertEquals("SP", endereco.uf());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveLancarExcecaoQuandoRuaForNulaOuVazia(String rua) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco(rua, "123", "Cidade", "12345-678", "SP"));
        assertEquals(Endereco.MSG_RUA_OBRIGATORIA, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveLancarExcecaoQuandoNumeroForNuloOuVazio(String numero) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", numero, "Cidade", "12345-678", "SP"));
        assertEquals(Endereco.MSG_NUMERO_OBRIGATORIO, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveLancarExcecaoQuandoCidadeForNulaOuVazia(String cidade) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", "123", cidade, "12345-678", "SP"));
        assertEquals(Endereco.MSG_CIDADE_OBRIGATORIA, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveLancarExcecaoQuandoCepForNuloOuVazio(String cep) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", "123", "Cidade", cep, "SP"));
        assertEquals(Endereco.MSG_CEP_OBRIGATORIO, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveLancarExcecaoQuandoUfForNulaOuVazia(String uf) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", "123", "Cidade", "12345-678", uf));
        assertEquals(Endereco.MSG_UF_OBRIGATORIA, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234-567",    // CEP curto
            "123456-78",   // formato inválido
            "12345678A",   // com letra
            "123456789",   // CEP longo
            "1234A-678",   // com letra no meio
            "12345-67A"    // com letra no final
    })
    void deveLancarExcecaoQuandoCepForInvalido(String cep) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", "123", "Cidade", cep, "SP"));
        assertEquals(Endereco.MSG_CEP_FORMATO_INVALIDO, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "S",      // UF muito curta
            "SPP",    // UF muito longa
            "S1",     // UF com número
            "@#"      // UF com caracteres especiais
    })
    void deveLancarExcecaoQuandoUfForInvalida(String uf) {
        // act & assert
        EnderecoInvalidoException exception = assertThrows(EnderecoInvalidoException.class,
                () -> new Endereco("Rua Teste", "123", "Cidade", "12345-678", uf));
        assertEquals(Endereco.MSG_UF_FORMATO_INVALIDO, exception.getMessage());
    }
}