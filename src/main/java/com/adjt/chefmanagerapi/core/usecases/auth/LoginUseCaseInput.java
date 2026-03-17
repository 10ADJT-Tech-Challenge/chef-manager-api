package com.adjt.chefmanagerapi.core.usecases.auth;

public record LoginUseCaseInput(
        String login,
        String senha
) {
}
