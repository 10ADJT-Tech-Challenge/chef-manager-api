package com.adjt.chefmanagerapi.core.domain.entities;

import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        // Arrange & Act
        TipoUsuario tipoUsuario = new TipoUsuario("Cliente Premium", CategoriaUsuario.CLIENTE);

        // Assert
        assertNotNull(tipoUsuario);
        assertEquals("Cliente Premium", tipoUsuario.getNome());
        assertEquals(CategoriaUsuario.CLIENTE, tipoUsuario.getCategoriaUsuario());
        assertNotNull(tipoUsuario.getId());
    }

    @Test
    void deveCriarDoisTipoUsuarioComIdsDiferentes() {
        // Arrange & Act
        TipoUsuario tipoUsuario1 = new TipoUsuario("Dono", CategoriaUsuario.DONO_RESTAURANTE);
        TipoUsuario tipoUsuario2 = new TipoUsuario("Dono", CategoriaUsuario.DONO_RESTAURANTE);

        // Assert
        assertNotEquals(tipoUsuario1.getId(), tipoUsuario2.getId());
    }

    @Test
    void deveCriarTipoUsuarioAdmin() {
        // Arrange & Act
        TipoUsuario tipoUsuario = new TipoUsuario("Administrador Master", CategoriaUsuario.ADMIN);

        // Assert
        assertEquals(CategoriaUsuario.ADMIN, tipoUsuario.getCategoriaUsuario());
        assertEquals("Administrador Master", tipoUsuario.getNome());
    }

    @Test
    void deveCriarTipoUsuarioDonoRestaurante() {
        // Arrange & Act
        TipoUsuario tipoUsuario = new TipoUsuario("Dono de Restaurante", CategoriaUsuario.DONO_RESTAURANTE);

        // Assert
        assertEquals(CategoriaUsuario.DONO_RESTAURANTE, tipoUsuario.getCategoriaUsuario());
        assertEquals("Dono de Restaurante", tipoUsuario.getNome());
    }

    @Test
    void deveCriarTipoUsuarioCliente() {
        // Arrange & Act
        TipoUsuario tipoUsuario = new TipoUsuario("Cliente VIP", CategoriaUsuario.CLIENTE);

        // Assert
        assertEquals(CategoriaUsuario.CLIENTE, tipoUsuario.getCategoriaUsuario());
        assertEquals("Cliente VIP", tipoUsuario.getNome());
    }
}