package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EmailInvalidoException;
import com.adjt.chefmanagerapi.core.exceptions.EmailObrigatorioException;

import java.util.regex.Pattern;

public record Email( String email) {
    /**
     * Regex com Quantificadores Possessivos (++ e *+)
     * 1. [a-zA-Z0-9_+&*-]++ : Pega a primeira parte do e-mail de forma possessiva.
     * 2. (?:\.[a-zA-Z0-9_+&*-]++)*+ : Repetição possessiva de subpartes com ponto.
     * 3. (?:[a-zA-Z0-9-]++\.)++ : Repetição possessiva para o domínio.
     */
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]++(?:\\.[a-zA-Z0-9_+&*-]++)*+@(?:[a-zA-Z0-9-]++\\.)++[a-zA-Z]{2,7}$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public Email {
        validarEmail(email);
    }

    private static void validarEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new EmailObrigatorioException();

        if (!PATTERN.matcher(email).matches())
            throw new EmailInvalidoException();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public String toString() {
        return email;
    }
}
