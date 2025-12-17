package com.adjt.chefmanagerapi.core.usecases.usuario.atualizar;

import com.adjt.chefmanagerapi.core.exceptions.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AtualizarUsuarioUseCase implements AtualizarUsuario {
    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway, UsuarioMapper mapper) {
        this.usuarioGateway = usuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public AtualizarUsuarioOutput executar(AtualizarUsuarioInput dto) {
        Usuario usuario = usuarioGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new NoSuchElementException("Nenhum usuário encontrado com o id: " + dto.id()));

        Optional.ofNullable(dto.nome()).ifPresent(usuario::setNome);
        Optional.ofNullable(dto.email()).ifPresent(email -> {
            validaEmailExistente(email, usuario);
            usuario.setEmail(email);
        });
        Optional.ofNullable(dto.login()).ifPresent(usuario::setLogin);
        Optional.ofNullable(dto.tipo()).ifPresent(usuario::setTipo);
        Optional.ofNullable(dto.endereco()).ifPresent(enderecoInput -> atualizaEndereco(enderecoInput, usuario.getEndereco()));

        usuarioGateway.salvar(usuario);
        return mapper.toAtualizarOutput(usuario);
    }

    private static void atualizaEndereco(AtualizarUsuarioInput.EnderecoInput enderecoInput, Endereco endereco) {
        Optional.ofNullable(enderecoInput.rua()).ifPresent(endereco::setRua);
        Optional.ofNullable(enderecoInput.numero()).ifPresent(endereco::setNumero);
        Optional.ofNullable(enderecoInput.cidade()).ifPresent(endereco::setCidade);
        Optional.ofNullable(enderecoInput.cep()).ifPresent(endereco::setCep);
        Optional.ofNullable(enderecoInput.uf()).ifPresent(endereco::setUf);
    }

    private void validaEmailExistente(String email, Usuario usuario) {
        if (usuarioGateway.buscarPorEmail(email).isPresent() && !usuario.getEmail().equals(email))
            throw new EmailJaCadastradoException(email);
    }
}
