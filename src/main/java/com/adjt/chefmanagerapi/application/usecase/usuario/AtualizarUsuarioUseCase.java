package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.exception.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.EnderecoInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.AtualizarUsuario;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.application.mapper.UsuarioApplicationMapper;
import com.adjt.chefmanagerapi.domain.Endereco;
import com.adjt.chefmanagerapi.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtualizarUsuarioUseCase implements AtualizarUsuario {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioApplicationMapper mapper;

    public AtualizarUsuarioUseCase(UsuarioRepository usuarioRepository, UsuarioApplicationMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioOutput executar(UUID id, AtualizarUsuarioInput dto) {
        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum usuário encontrado com o id: " + id));

        Optional.ofNullable(dto.nome()).ifPresent(usuario::setNome);
        Optional.ofNullable(dto.email()).ifPresent(email -> {
            validaEmailExistente(email, usuario);
            usuario.setEmail(email);
        });
        Optional.ofNullable(dto.login()).ifPresent(usuario::setLogin);
        Optional.ofNullable(dto.tipo()).ifPresent(usuario::setTipo);
        Optional.ofNullable(dto.endereco()).ifPresent(enderecoInput -> atualizaEndereco(enderecoInput, usuario.getEndereco()));

        usuarioRepository.salvar(usuario);
        return mapper.toOutput(usuario);
    }

    private static void atualizaEndereco(EnderecoInput enderecoInput, Endereco endereco) {
        Optional.ofNullable(enderecoInput.rua()).ifPresent(endereco::setRua);
        Optional.ofNullable(enderecoInput.numero()).ifPresent(endereco::setNumero);
        Optional.ofNullable(enderecoInput.cidade()).ifPresent(endereco::setCidade);
        Optional.ofNullable(enderecoInput.cep()).ifPresent(endereco::setCep);
        Optional.ofNullable(enderecoInput.uf()).ifPresent(endereco::setUf);
    }

    private void validaEmailExistente(String email, Usuario usuario) {
        if (usuarioRepository.buscarPorEmail(email).isPresent() && !usuario.getEmail().equals(email))
            throw new EmailJaCadastradoException(email);
    }
}
