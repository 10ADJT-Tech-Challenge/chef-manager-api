package com.adjt.chefmanagerapi.core.usecases.tipousuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioInput;

import java.util.UUID;

import static com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario.TipoUsuarioRequestHelper.*;

public abstract class TipoUsuarioHelper {

    public static final TipoUsuario TIPO_USUARIO_ADMIN = new TipoUsuario(
            UUID.fromString(UUID_TIPO_USUARIO_ADMIN),
            "ADMIN",
            CategoriaUsuario.ADMIN
    );
    public static final TipoUsuario TIPO_USUARIO_CLIENTE = new TipoUsuario(
            UUID.fromString(UUID_TIPO_USUARIO_CLIENTE),
            "Cliente",
            CategoriaUsuario.CLIENTE
    );
    public static final TipoUsuario TIPO_USUARIO_DONO = new TipoUsuario(
            UUID.fromString(UUID_TIPO_USUARIO_DONO_RESTAURANTE),
            "Dono Restaurante",
            CategoriaUsuario.DONO_RESTAURANTE
    );

    public abstract static class CadastrarTipoUsuarioHelper {
        public static CadastrarTipoUsuarioInput criarInputClienteValido() {
            return new CadastrarTipoUsuarioInput("UsuarioTeste", "CLIENTE");
        }

        public static CadastrarTipoUsuarioInput criarInputDonoRestauranteValido() {
            return new CadastrarTipoUsuarioInput("DonoTeste", "DONO_RESTAURANTE");
        }

        public static CadastrarTipoUsuarioInput criarInputAdministradorValido() {
            return new CadastrarTipoUsuarioInput("AdminTeste", "ADMIN");
        }
    }

    public abstract static class AtualizarTipoUsuarioHelper {

        public static AtualizarTipoUsuarioInput criarInputClienteValido(UUID id) {
            return new AtualizarTipoUsuarioInput(id, "UsuarioAtualizado", "CLIENTE");
        }

        public static AtualizarTipoUsuarioInput criarInputDonoRestauranteValido(UUID id) {
            return new AtualizarTipoUsuarioInput(id, "DonoAtualizado", "DONO_RESTAURANTE");
        }

        public static AtualizarTipoUsuarioInput criarInputAdministradorValido(UUID id) {
            return new AtualizarTipoUsuarioInput(id, "AdminAtualizado", "ADMIN");
        }
    }
}
