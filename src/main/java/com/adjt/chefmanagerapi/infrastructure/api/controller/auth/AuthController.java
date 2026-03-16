package com.adjt.chefmanagerapi.infrastructure.api.controller.auth;

import com.adjt.chefmanagerapi.core.usecases.auth.LoginUseCase;
import com.adjt.chefmanagerapi.infrastructure.api.controller.auth.dto.AuthResponse;
import com.adjt.chefmanagerapi.infrastructure.api.controller.auth.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return loginUseCase.execute(request);
    }
}