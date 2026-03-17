package com.adjt.chefmanagerapi.core.usecases.auth;

public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}