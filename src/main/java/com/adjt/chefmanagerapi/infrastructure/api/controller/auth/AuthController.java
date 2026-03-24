package com.adjt.chefmanagerapi.infrastructure.api.controller.auth;

import com.adjt.chefmanagerapi.core.usecases.usuario.login.LoginInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.login.LoginUsuarioUseCase;
import com.adjt.chefmanagerapi.model.LoginAuthResponse;
import com.adjt.chefmanagerapi.model.LoginRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public AuthController(LoginUsuarioUseCase loginUsuarioUseCase) {
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }

    @PostMapping("/login")
    public LoginAuthResponse login(@RequestBody LoginRequest request) {
        var output = loginUsuarioUseCase.execute(
                new LoginInput(request.getEmail(), request.getSenha())
        );

        return new LoginAuthResponse(output.token(), "Bearer");
    }
}
