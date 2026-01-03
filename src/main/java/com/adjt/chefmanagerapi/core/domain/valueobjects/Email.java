package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EmailObrigatorioException;

import java.util.regex.Pattern;

public record Email( String email) {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public Email {
        validarEmail(email);
    }

    private static void validarEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new EmailObrigatorioException();

        if (!PATTERN.matcher(email).matches())
            throw new IllegalArgumentException("Formato de email inválido. Exemplo correto: usuario@dominio.com.br");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public String toString() {
        return email;
    }
}
