package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Administrador;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestauranteTest {

    private static final String NOME = "Restaurante ABC";
    private static final String ENDERECO = "Rua X";
    private static final String TIPO_COZINHA = "Italiana";
    private static final String HORARIO_FUNCIONAMENTO = "10h às 12h";
    private static final Usuario USUARIO = new Administrador(
            UUID.randomUUID(),
            "Nome",
            "email@email.com",
            "login",
            "senha123",
            null,
            null
    );

    @Test
    void deveCriarRestauranteComSucesso() {
        // arrange & act
        Restaurante restaurante = new Restaurante(NOME, ENDERECO, TIPO_COZINHA, HORARIO_FUNCIONAMENTO, USUARIO);

        // assert
        assertNotNull(restaurante);
        assertNotNull(restaurante.getId());
        assertEquals(NOME, restaurante.getNome());
        assertEquals(ENDERECO, restaurante.getEndereco());
        assertEquals(TIPO_COZINHA, restaurante.getTipoCozinha());
        assertEquals(HORARIO_FUNCIONAMENTO, restaurante.getHorarioFuncionamento());
        assertEquals(USUARIO, restaurante.getResponsavel());
        assertNotNull(restaurante.getDataUltimaAlteracao());
    }
}