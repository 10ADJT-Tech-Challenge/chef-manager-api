package com.adjt.chefmanagerapi.core.usecases.usuario.login;

import com.adjt.chefmanagerapi.core.exceptions.EmailInvalidoException;
import com.adjt.chefmanagerapi.core.exceptions.SenhaInvalidaException;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.interfaces.TokenGeneratorGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;

public class LoginUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final SenhaEncoderGateway senhaEncoder;
    private final TokenGeneratorGateway tokenGenerator;

    public LoginUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            SenhaEncoderGateway senhaEncoder,
            TokenGeneratorGateway tokenGenerator
    ) {
        this.usuarioGateway = usuarioGateway;
        this.senhaEncoder = senhaEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public LoginOutput execute(LoginInput input) {
        var usuario = usuarioGateway.buscarPorEmail(input.email())
                .orElseThrow(() -> new EmailInvalidoException());

        if (!senhaEncoder.verifica(input.senha(), usuario.getSenha())) {
            throw new SenhaInvalidaException();
        }

        String token = tokenGenerator.gerar(usuario);

        return new LoginOutput(token);
    }
}