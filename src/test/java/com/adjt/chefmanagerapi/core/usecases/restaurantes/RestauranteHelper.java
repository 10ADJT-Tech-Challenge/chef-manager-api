package com.adjt.chefmanagerapi.core.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.DonoRestaurante;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar.AtualizarRestauranteInput;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar.CadastrarRestauranteInput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioHelper;

import java.util.UUID;

public abstract class RestauranteHelper {

    public abstract static class CadastrarRestauranteHelper {

        public static CadastrarRestauranteInput criarInputValido() {
            return new CadastrarRestauranteInput(
                    "Restaurante X",
                    "Rua Principal",
                    "Italiana",
                    "10h às 12h",
                    UUID.randomUUID()
            );
        }

        public static CadastrarRestauranteInput criarInputComNomeNull() {
            return new CadastrarRestauranteInput(
                    null,
                    "Rua Principal",
                    "Italiana",
                    "10h às 12h",
                    UUID.randomUUID()
            );
        }

        public static CadastrarRestauranteInput criarInputComEnderecoVazio() {
            return new CadastrarRestauranteInput(
                    "Restaurante X",
                    null,
                    "Italiana",
                    "10h às 12h",
                    UUID.randomUUID()
            );
        }

        public static CadastrarRestauranteInput criarInputComTipoCozinhaNull() {
            return new CadastrarRestauranteInput(
                    "Restaurante X",
                    "Rua Principal",
                    null,
                    "10h às 12h",
                    UUID.randomUUID()
            );
        }

        public static CadastrarRestauranteInput criarInputComHorarioFuncionamentoNull() {
            return new CadastrarRestauranteInput(
                    "Restaurante X",
                    "Rua Principal",
                    "Italiana",
                    null,
                    UUID.randomUUID()
            );
        }
    }

    public abstract static class AtualizarRestauranteHelper {

        public static AtualizarRestauranteInput criarInputAtualizacaoCompleta(UUID id) {
            return new AtualizarRestauranteInput(
                    id,
                    "Novo Nome",
                    "Novo endereço",
                    "Nova cozinha",
                    "novo horario"
            );
        }
    }

    public static Restaurante criarRestauranteSimulado(CadastrarRestauranteInput input) {
        return new Restaurante(
                UUID.randomUUID(),
                input.nome(),
                input.endereco(),
                input.tipoCozinha(),
                input.horarioFuncionamento(),
                new DonoRestaurante("Dono", "email@email.com", "login", "senha123", null, TipoUsuarioHelper.TIPO_USUARIO_DONO)
        );
    }

    public static Restaurante criarRestauranteSimulado(AtualizarRestauranteInput input) {
        return new Restaurante(
                UUID.randomUUID(),
                input.nome(),
                input.endereco(),
                input.tipoCozinha(),
                input.horarioFuncionamento(),
                new DonoRestaurante("Dono", "email@email.com", "login", "senha123", null, TipoUsuarioHelper.TIPO_USUARIO_DONO)
        );
    }

    public static Restaurante buscaRestaurante() {
        return criarRestauranteSimulado(RestauranteHelper.CadastrarRestauranteHelper.criarInputValido());
    }

    public static DonoRestaurante donoRestaurante = new DonoRestaurante("Dono", "email@email.com", "login", "senha123", null, TipoUsuarioHelper.TIPO_USUARIO_DONO);
}
