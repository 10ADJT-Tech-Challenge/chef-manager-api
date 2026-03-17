package com.adjt.chefmanagerapi.core.usecases.auth;


public record LoginUseCaseOutput(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}