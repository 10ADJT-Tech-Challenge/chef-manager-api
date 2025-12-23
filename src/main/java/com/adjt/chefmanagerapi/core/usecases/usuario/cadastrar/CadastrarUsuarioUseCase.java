package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarUsuarioUseCase implements CadastrarUsuario {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;
    private final SenhaEncoderGateway senhaEncoderGateway;
//    private final UsuarioAutenticadoProvider autenticadoProvider;

    public CadastrarUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            SenhaEncoderGateway senhaEncoderGateway,
            UsuarioMapper mapper
//            ,UsuarioAutenticadoProvider autenticadoProvider
    ) {
        this.usuarioGateway = usuarioGateway;
        this.senhaEncoderGateway = senhaEncoderGateway;
        this.mapper = mapper;
//        this.autenticadoProvider = autenticadoProvider;
    }

    @Override
    @Transactional
    public CadastrarUsuarioOutput executar(CadastrarUsuarioInput input) {
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

        Usuario usuario = usuarioGateway.salvar(
                new Usuario(
                        input.nome(),
                        input.email(),
                        input.login(),
                        getHashSenha(input.senha()),
                        tipoUsuario,
                        endereco
                )
        );

        return mapper.toCadastrarOutput(usuario);
    }

    private String getHashSenha(String senha) {
        return senhaEncoderGateway.encode(senha);
    }

    private void validaEmail(String email) {
        if (email == null || email.isBlank())
            throw new EmailObrigatorioException();

        validaEmailExistente(email);
    }

    private void validaEmailExistente(String email) {
        if (usuarioGateway.buscarPorEmail(email).isPresent()) {
            throw new EmailJaCadastradoException(email);
        }
    }

    private void validaLogin(String login) {
        if (login == null || login.isBlank())
            throw new LoginObrigatorioException();

        validaLoginExistente(login);
    }

    private void validaLoginExistente(String login) {
        if (usuarioGateway.buscarPorLogin(login).isPresent()) {
            throw new LoginJaCadastradoException(login);
        }
    }

    private static void validaSenha(String senha) {
        if (senha == null || senha.length() < 6)
            throw new SenhaInvalidaException();
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
