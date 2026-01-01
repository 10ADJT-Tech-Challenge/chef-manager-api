package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenhaInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioInput;

import java.util.UUID;

public abstract class UsuarioHelper {

    public abstract static class CadastrarUsuarioHelper {

        public static CadastrarUsuarioInput criarInputValido(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "joao@email.com",
                    "joao.silva",
                    "senha123",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComEmailNull(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    null,
                    "joao.silva",
                    "senha123",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComEmailVazio(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "",
                    "joao.silva",
                    "senha123",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComLoginNull(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "joao@email.com",
                    null,
                    "senha123",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComLoginVazio(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "joao@email.com",
                    "  ",
                    "senha123",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComSenhaCurta(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "joao@email.com",
                    "joao.silva",
                    "senha",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }

        public static CadastrarUsuarioInput criarInputComSenhaNull(String tipo) {
            return new CadastrarUsuarioInput(
                    "João Silva",
                    "joao@email.com",
                    "joao.silva",
                    "senha",
                    tipo,
                    new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
            );
        }
    }

    public abstract static class AtualizarUsuarioHelper {

        public static AtualizarUsuarioInput criarInputAtualizacaoCompleta(UUID id, String novoTipo) {
            return new AtualizarUsuarioInput(
                    id,
                    "Novo Nome",
                    "novo@email.com",
                    "novo.login",
                    novoTipo,
                    getNovoEnderecoInput()
            );
        }

        public static AtualizarUsuarioInput criarInputAtualizacaoComMesmoEmailELogin(UUID id, String novoTipo) {
            return new AtualizarUsuarioInput(
                    id,
                    "Novo Nome",
                    "joao@email.com",
                    "joao.silva",
                    novoTipo,
                    getNovoEnderecoInput()
            );
        }

        public static AtualizarUsuarioInput criarInputAtualizacaoApenasLogin(UUID id) {
            return new AtualizarUsuarioInput(
                    id,
                    null,
                    null,
                    "novo.login",
                    null,
                    null
            );
        }

        private static AtualizarUsuarioInput.EnderecoInput getNovoEnderecoInput() {
            return new AtualizarUsuarioInput.EnderecoInput(
                    "Nova Rua",
                    "456",
                    "Nova Cidade",
                    "88000-000",
                    "SC");
        }
    }

    public abstract static class AlterarSenhaHelper {

        public static AlterarSenhaInput criarInputAlteracaoSenha(UUID id) {
            return new AlterarSenhaInput(id, "senha123", "novaSenha123");
        }

        public static AlterarSenhaInput criarInputAlteracaoSenhaIncorreta(UUID id) {
            return new AlterarSenhaInput(id, "senhaErrada", "novaSenha123");
        }
    }

    public static Usuario criarUsuarioSimulado(CadastrarUsuarioInput input) {
        // Simula o objeto que o Gateway retornaria após salvar no banco (com ID gerado)
        // A senha salva já estaria hasheada
        return UsuarioFactory.criarUsuario(
                input.nome(),
                input.email(),
                input.login(),
                "HASH_SENHA_MOCK", // A senha salva já estaria hasheada
                input.tipo(),
                input.endereco().rua(),
                input.endereco().numero(),
                input.endereco().cidade(),
                input.endereco().cep(),
                input.endereco().uf()
        );
    }

    public static Usuario buscaUsuario(String tipo) {
        return criarUsuarioSimulado(CadastrarUsuarioHelper.criarInputValido(tipo));
    }
}
