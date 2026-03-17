package com.adjt.chefmanagerapi.core.usecases.auth;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.infrastructure.security.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUseCase {

    private final UsuarioGateway usuarioGateway;
    private final SenhaEncoderGateway senhaEncoderGateway;
    private final JwtService jwtService;

    public LoginUseCase(
            UsuarioGateway usuarioGateway,
            SenhaEncoderGateway senhaEncoderGateway,
            JwtService jwtService
    ) {
        this.usuarioGateway = usuarioGateway;
        this.senhaEncoderGateway = senhaEncoderGateway;
        this.jwtService = jwtService;
    }

    public AuthResponse execute(LoginRequest request) {
        Usuario usuario = usuarioGateway.buscarPorLogin(request.login())
                .orElseThrow(() -> new IllegalArgumentException("Usuário inexistente ou senha inválida"));

        if (!senhaEncoderGateway.verifica(request.senha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Usuário inexistente ou senha inválida");
        }

        String role = resolveRole(usuario);

        String token = jwtService.generateToken(
                usuario.getId(),
                usuario.getLogin(),
                List.of(role)
        );

        return new AuthResponse(token, "Bearer", jwtService.getExpirationSeconds());
    }

    private String resolveRole(Usuario usuario) {
        if (usuario.getTipo() == null || usuario.getTipo().getCategoriaUsuario() == null) {
            return "ROLE_CLIENTE";
        }

        return switch (usuario.getTipo().getCategoriaUsuario()) {
            case ADMIN -> "ROLE_ADMIN";
            case DONO_RESTAURANTE -> "ROLE_DONO_RESTAURANTE";
            case CLIENTE -> "ROLE_CLIENTE";
        };
    }
}