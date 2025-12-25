package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
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

        public static Usuario buscaUsuarioParaAtualizar(String tipo) {
            return criarUsuarioSimulado(CadastrarUsuarioHelper.criarInputValido(tipo));
        }

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

    public static Usuario criarUsuarioSimulado(CadastrarUsuarioInput input) {
        // Simula o objeto que o Gateway retornaria após salvar no banco (com ID gerado)
        Usuario usuario = new Usuario(
                input.nome(),
                input.email(),
                input.login(),
                "HASH_SENHA_MOCK", // A senha salva já estaria hasheada
                TipoUsuario.valueOf(input.tipo()),
                new Endereco(
                        input.endereco().rua(),
                        input.endereco().numero(),
                        input.endereco().cidade(),
                        input.endereco().cep(),
                        input.endereco().uf()
                )
        );
         usuario.setId(UUID.randomUUID());
        return usuario;
    }

}
