package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.SenhaInvalidaException;

public record Senha(String senha) {
    private static final int TAMANHO_MINIMO_SENHA = 6;

    public Senha {
        validaSenha(senha);
    }

    private void validaSenha(String senha) {
        if (senha == null || senha.length() < TAMANHO_MINIMO_SENHA)
            throw new SenhaInvalidaException();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public String toString() {
        return senha;
    }
}
