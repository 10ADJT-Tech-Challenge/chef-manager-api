package com.adjt.chefmanagerapi.unit.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioInput;

public abstract class CadastrarUsuarioHelper {

    public static CadastrarUsuarioInput criarInputValido(String tipo) {
        return new CadastrarUsuarioInput(
                "João Silva",
                "joao@email.com",
                "joao.silva",
                "senha123", // > 6 caracteres
                tipo,
                new CadastrarUsuarioInput.EnderecoInput("Rua A", "123", "Cidade", "87000-000", "PR")
        );
    }

    public static Usuario criarUsuarioSimulado(CadastrarUsuarioInput input) {
        // Simula o objeto que o Gateway retornaria após salvar no banco (com ID gerado)
        var usuario = new Usuario(
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
        // Precisamos simular que o ID foi gerado (se sua entidade Usuario tiver setter ou reflexão,
        // caso contrário, ajuste conforme seu construtor real)
        // usuario.setId(UUID.randomUUID());
        return usuario;
    }

}
