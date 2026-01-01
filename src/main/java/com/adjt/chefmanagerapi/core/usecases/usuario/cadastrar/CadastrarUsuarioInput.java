package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

public record CadastrarUsuarioInput(
        String nome,
        String email,
        String login,
        String senha,
        String tipo,
        EnderecoInput endereco
) {
    public record EnderecoInput(
            String rua,
            String numero,
            String cidade,
            String cep,
            String uf
    ) {}
}