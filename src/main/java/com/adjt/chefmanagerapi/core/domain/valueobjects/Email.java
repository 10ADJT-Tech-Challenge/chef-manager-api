package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EmailObrigatorioException;

public record Email( String email) {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Email {
        validarEmail(email);
    }

    private static void validarEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new EmailObrigatorioException();

        if (!email.matches(EMAIL_PATTERN))
            throw new IllegalArgumentException("Formato de email inválido. Exemplo correto: usuario@dominio.com.br");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public String toString() {
        return email;
    }
}
