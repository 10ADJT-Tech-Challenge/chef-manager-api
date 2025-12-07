package com.adjt.chefmanagerapi.core.domain.usecases.usuarios;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.AlterarSenhaInput;
import com.adjt.chefmanagerapi.core.adapters.interfaces.AlterarSenha;
import com.adjt.chefmanagerapi.core.adapters.gateways.UsuarioGateway;
import com.adjt.chefmanagerapi.core.adapters.gateways.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AlterarSenhaUseCase implements AlterarSenha {

    private final UsuarioGateway repository;
    private final SenhaEncoderGateway senhaEncoderGateway;

    public AlterarSenhaUseCase(UsuarioGateway repository, SenhaEncoderGateway senhaEncoderGateway) {
        this.repository = repository;
        this.senhaEncoderGateway = senhaEncoderGateway;
    }

    @Override
    public void executar(AlterarSenhaInput input) {
        Usuario usuario = repository.buscarPorId(input.usuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        if (!senhaEncoderGateway.verifica(input.senhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        String novaSenhaHash = senhaEncoderGateway.encode(input.novaSenha());
        usuario.alterarSenha(novaSenhaHash);

        repository.salvar(usuario);
    }
}

