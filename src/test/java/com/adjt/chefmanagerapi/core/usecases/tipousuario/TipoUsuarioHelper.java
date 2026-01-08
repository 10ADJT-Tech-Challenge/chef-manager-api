package com.adjt.chefmanagerapi.core.usecases.tipousuario;

import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioInput;

import java.util.UUID;

public abstract class TipoUsuarioHelper {

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
