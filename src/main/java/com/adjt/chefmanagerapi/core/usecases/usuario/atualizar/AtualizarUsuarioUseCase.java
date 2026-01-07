package com.adjt.chefmanagerapi.core.usecases.usuario.atualizar;

import com.adjt.chefmanagerapi.core.exceptions.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.core.exceptions.LoginJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class AtualizarUsuarioUseCase implements AtualizarUsuario {
    private static final String MENSAGEM_USUARIO_NAO_ENCONTRADO = "Nenhum usuário encontrado com o id: ";

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway, UsuarioMapper mapper) {
        this.usuarioGateway = usuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public AtualizarUsuarioOutput executar(AtualizarUsuarioInput dto) {
        Usuario usuario = buscaUsuarioOuLancaErro(dto.id());
        usuario = atualizarDadosUsuario(usuario, dto);

        return mapper.toAtualizarOutput(usuarioGateway.salvar(usuario));
    }

    private Usuario atualizarDadosUsuario(Usuario usuario, AtualizarUsuarioInput dto) {
        if (dto.tipo() != null)
            usuario = usuario.atualizarTipo(dto.tipo());

        atualizaNomeSePresenteEValido(usuario, dto.nome());
        atualizaEmailSePresenteEValido(usuario, dto.email());
        atualizaLoginSePresenteEValido(usuario, dto.login());
        atualizaEnderecoSePresenteEValido(usuario, dto.endereco());
        return usuario;
    }

    private void atualizaEnderecoSePresenteEValido(Usuario usuario, AtualizarUsuarioInput.EnderecoInput endereco) {
        Optional.ofNullable(endereco).ifPresent(enderecoInput -> atualizaEndereco(enderecoInput, usuario));
    }

    private void atualizaLoginSePresenteEValido(Usuario usuario, String login) {
        Optional.ofNullable(login).ifPresent(l -> {
            validaLoginExistente(l, usuario);
            usuario.atualizarLogin(l);
        });
    }

    private void atualizaEmailSePresenteEValido(Usuario usuario, String email) {
        Optional.ofNullable(email).ifPresent(e -> {
            validaEmailExistente(e, usuario);
            usuario.atualizarEmail(e);
        });
    }

    private void atualizaNomeSePresenteEValido(Usuario usuario, String nome) {
        Optional.ofNullable(nome).ifPresent(usuario::atualizarNome);
    }

    private Usuario buscaUsuarioOuLancaErro(UUID id) {
        return usuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_USUARIO_NAO_ENCONTRADO + id));
    }

    private static void atualizaEndereco(AtualizarUsuarioInput.EnderecoInput enderecoInput, Usuario usuario) {
        Endereco enderecoAtual = usuario.getEndereco();
        usuario.atualizarEndereco(
                new Endereco(
                        obterValorEndereco(enderecoInput::rua, enderecoAtual::rua),
                        obterValorEndereco(enderecoInput::numero, enderecoAtual::numero),
                        obterValorEndereco(enderecoInput::cidade, enderecoAtual::cidade),
                        obterValorEndereco(enderecoInput::cep, enderecoAtual::cep),
                        obterValorEndereco(enderecoInput::uf, enderecoAtual::uf)
                )
        );
    }

    private void validaEmailExistente(String email, Usuario usuario) {
        Optional<Usuario> buscadoPorEmail = usuarioGateway.buscarPorEmail(email);
        if (buscadoPorEmail.isPresent() && !usuario.getId().equals(buscadoPorEmail.get().getId()))
            throw new EmailJaCadastradoException(email);
    }

    private void validaLoginExistente(String login, Usuario usuario) {
        Optional<Usuario> buscadoPorLogin = usuarioGateway.buscarPorLogin(login);
        if (buscadoPorLogin.isPresent() && !usuario.getId().equals(buscadoPorLogin.get().getId()))
            throw new LoginJaCadastradoException(login);
    }

    private static String obterValorEndereco(Supplier<String> novoValor, Supplier<String> valorAtual) {
        return Optional.ofNullable(novoValor.get()).orElse(valorAtual.get());
    }
}
