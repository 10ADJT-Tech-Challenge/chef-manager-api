package com.adjt.chefmanagerapi.infrastructure.api.controller.auth.dto;

public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}