package com.adjt.chefmanagerapi.application.usecase.usuario;

import com.adjt.chefmanagerapi.application.exception.*;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.application.gateway.inbound.usecase.usuario.CadastrarUsuario;
import com.adjt.chefmanagerapi.application.gateway.outbound.repository.UsuarioRepository;
import com.adjt.chefmanagerapi.application.gateway.outbound.security.SenhaEncoder;
import com.adjt.chefmanagerapi.application.mapper.UsuarioApplicationMapper;
import com.adjt.chefmanagerapi.domain.Endereco;
import com.adjt.chefmanagerapi.domain.TipoUsuario;
import com.adjt.chefmanagerapi.domain.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarUsuarioUseCase implements CadastrarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioApplicationMapper mapper;
    private final SenhaEncoder senhaEncoder;
//    private final UsuarioAutenticadoProvider autenticadoProvider;

    public CadastrarUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            SenhaEncoder senhaEncoder,
            UsuarioApplicationMapper mapper
//            ,UsuarioAutenticadoProvider autenticadoProvider
    ) {
        this.usuarioRepository = usuarioRepository;
        this.senhaEncoder = senhaEncoder;
        this.mapper = mapper;
//        this.autenticadoProvider = autenticadoProvider;
    }

    @Override
    @Transactional
    public UsuarioOutput executar(CadastrarUsuarioInput input) {
        validaEmail(input.email());
        validaLogin(input.login());
        validaSenha(input.senha());
//        validarPermissoesParaCriacao(input.tipo());

        Endereco endereco = new Endereco(
                input.endereco().rua(),
                input.endereco().numero(),
                input.endereco().cidade(),
                input.endereco().cep(),
                input.endereco().uf()
        );

        TipoUsuario tipoUsuario = TipoUsuario.valueOf(input.tipo().toUpperCase());

        Usuario usuario = usuarioRepository.salvar(
                new Usuario(
                        input.nome(),
                        input.email(),
                        input.login(),
                        getHashSenha(input.senha()),
                        tipoUsuario,
                        endereco
                )
        );

        return mapper.toOutput(usuario);
    }

    private String getHashSenha(String senha) {
        return senhaEncoder.encode(senha);
    }

    private void validaEmail(String email) {
        Optional.ofNullable(email).ifPresentOrElse(this::validaEmailExistente, () -> {
            throw new EmailObrigatorioException();
        });
    }

    private void validaEmailExistente(String email) {
        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new EmailJaCadastradoException(email);
        }
    }

    private void validaLogin(String login) {
        Optional.ofNullable(login).ifPresentOrElse(this::validaLoginExistente, () -> {
            throw new LoginObrigatorioException();
        });
    }

    private void validaLoginExistente(String login) {
        if (usuarioRepository.buscarPorLogin(login).isPresent()) {
            throw new LoginJaCadastradoException(login);
        }
    }

    private static void validaSenha(String senha) {
        if (senha == null || senha.length() < 6) {
            throw new SenhaInvalidaException();
        }
    }

    /*private void validarPermissoesParaCriacao(String tipoRequisitado) {
        Optional<String> loginAtual = autenticadoProvider.obterLoginAtual();

        if (loginAtual.isEmpty()) {
            if (!tipoRequisitado.equalsIgnoreCase("CLIENTE")) {
                throw new PermissaoNegadaException("Apenas usuários CLIENTE podem se registrar sem autenticação.");
            }
            return;
        }

        Usuario usuarioLogado = usuarioRepository.buscarPorLogin(loginAtual.get())
                .orElseThrow(() -> new PermissaoNegadaException("Usuário autenticado não encontrado."));

        TipoUsuario tipoLogado = usuarioLogado.getTipo();
        TipoUsuario tipoSolicitado = TipoUsuario.valueOf(tipoRequisitado.toUpperCase());

        switch (tipoLogado) {
            case ADMIN:
            case DONO_RESTAURANTE:
                break;
            case CLIENTE:
                if (!tipoSolicitado.equals(TipoUsuario.CLIENTE)) {
                    throw new PermissaoNegadaException("Usuários CLIENTE só podem criar novos usuários CLIENTE.");
                }
                break;
            default:
                throw new PermissaoNegadaException("Tipo de usuário não autorizado.");
        }
    }*/
}
