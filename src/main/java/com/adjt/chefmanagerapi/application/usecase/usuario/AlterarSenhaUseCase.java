package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.AlterarSenhaInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.AlterarSenha;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.application.gateway.outbound.security.SenhaEncoder;
import com.adjt.chefmanagerapi.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AlterarSenhaUseCase implements AlterarSenha {

    private final UsuarioRepository repository;
    private final SenhaEncoder senhaEncoder;

    public AlterarSenhaUseCase(UsuarioRepository repository, SenhaEncoder senhaEncoder) {
        this.repository = repository;
        this.senhaEncoder = senhaEncoder;
    }

    @Override
    public void executar(AlterarSenhaInput input) {
        Usuario usuario = repository.buscarPorId(input.usuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        if (!senhaEncoder.verifica(input.senhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        String novaSenhaHash = senhaEncoder.encode(input.novaSenha());
        usuario.alterarSenha(novaSenhaHash);

        repository.salvar(usuario);
    }
}

