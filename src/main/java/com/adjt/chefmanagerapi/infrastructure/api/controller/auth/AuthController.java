package com.adjt.chefmanagerapi.infrastructure.api.controller.auth;


import com.adjt.chefmanagerapi.AuthApi;

import com.adjt.chefmanagerapi.core.usecases.auth.LoginUseCase;
import com.adjt.chefmanagerapi.model.AuthResponse;
import com.adjt.chefmanagerapi.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final LoginUseCase loginUseCase;
    private final AuthApiMapper mapper;

    public AuthController(LoginUseCase loginUseCase, AuthApiMapper mapper) {
        this.loginUseCase = loginUseCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        var output = loginUseCase.execute(mapper.toUseCaseInput(loginRequest));

        AuthResponse response = new AuthResponse();
        response.setAccessToken(output.accessToken());
        response.setTokenType(output.tokenType());
        response.setExpiresIn(output.expiresIn());

        return ResponseEntity.ok(response);
    }
}