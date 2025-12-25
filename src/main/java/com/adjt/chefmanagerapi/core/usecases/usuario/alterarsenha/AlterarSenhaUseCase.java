package com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AlterarSenhaUseCase implements AlterarSenha {

    private final UsuarioGateway gateway;
    private final SenhaEncoderGateway senhaEncoderGateway;

    public AlterarSenhaUseCase(UsuarioGateway gateway, SenhaEncoderGateway senhaEncoderGateway) {
        this.gateway = gateway;
        this.senhaEncoderGateway = senhaEncoderGateway;
    }

    @Override
    public Void executar(AlterarSenhaInput input) {
        Usuario usuario = gateway.buscarPorId(input.usuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        if (!senhaEncoderGateway.verifica(input.senhaAtual(), usuario.getSenha()))
            throw new IllegalArgumentException("Senha atual incorreta");

        String novaSenhaHash = senhaEncoderGateway.encode(input.novaSenha());
        usuario.alterarSenha(novaSenhaHash);

        gateway.salvar(usuario);
        return null;
    }
}

