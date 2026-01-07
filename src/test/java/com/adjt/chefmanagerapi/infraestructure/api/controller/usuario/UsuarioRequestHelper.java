package com.adjt.chefmanagerapi.infraestructure.api.controller.usuario;

import com.adjt.chefmanagerapi.model.AlterarSenhaRequest;
import com.adjt.chefmanagerapi.model.AtualizarUsuarioRequest;
import com.adjt.chefmanagerapi.model.EnderecoRequest;
import com.adjt.chefmanagerapi.model.UsuarioRequest;

public abstract class UsuarioRequestHelper {

    static final String UUID_MARIA_SILVA = "cec64cf0-6dc9-4b4e-b0b8-405870ae1b43";
    static final String UUID_JOAO_SILVA = "f6f2a623-b22b-4494-bb35-cff956c86e5c";
    static final String UUID_PEDRO_SILVA = "e98915ad-2a2f-4463-ac5f-38258d3ffa5d";

    static UsuarioRequest getUsuarioRequest() {
        return new UsuarioRequest()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .login("joao.silva")
                .tipo(UsuarioRequest.TipoEnum.CLIENTE)
                .endereco(
                        new EnderecoRequest()
                                .cep("12345000")
                                .rua("Rua A")
                                .numero("123")
                                .cidade("Cidade C")
                                .uf("PR"));
    }

    static AtualizarUsuarioRequest getAtualizarUsuarioRequest() {
        return new AtualizarUsuarioRequest()
                .nome("Maria Silva Oliveira")
                .email("maria.silva.oliveira@email.com")
                .login("maria.silva.oliveira")
                .tipo(AtualizarUsuarioRequest.TipoEnum.DONO_RESTAURANTE)
                .endereco(
                        new EnderecoRequest()
                                .cep("12345-000")
                                .rua("Rua A")
                                .numero("123")
                                .cidade("Cidade C")
                                .uf("PR"));
    }

    static AlterarSenhaRequest getAlterarSenhaRequest() {
        return new AlterarSenhaRequest("secret123", "senhaNova123");
    }
}
