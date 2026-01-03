package com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Senha;
import com.adjt.chefmanagerapi.core.exceptions.EmailJaCadastradoException;
import com.adjt.chefmanagerapi.core.exceptions.LoginJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.interfaces.SenhaEncoderGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.usuario.UsuarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioUseCase implements CadastrarUsuario {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper mapper;
    private final SenhaEncoderGateway senhaEncoderGateway;

    public CadastrarUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            SenhaEncoderGateway senhaEncoderGateway,
            UsuarioMapper mapper
    ) {
        this.usuarioGateway = usuarioGateway;
        this.senhaEncoderGateway = senhaEncoderGateway;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CadastrarUsuarioOutput executar(CadastrarUsuarioInput input) {
        Senha senha = new Senha(input.senha());
        Usuario usuario = UsuarioFactory.criarUsuario(
                input.nome(),
                input.email(),
                input.login(),
                senhaEncoderGateway.encode(senha.toString()),
                input.tipo(),
                new Endereco(input.endereco().rua(),
                        input.endereco().numero(),
                        input.endereco().cidade(),
                        input.endereco().cep(),
                        input.endereco().uf())
        );

        validaEmailExistente(input.email());
        validaLoginExistente(input.login());

        return mapper.toCadastrarOutput(usuarioGateway.salvar(usuario));
    }

    private void validaEmailExistente(String email) {
        if (usuarioGateway.existePorEmail(email))
            throw new EmailJaCadastradoException(email);
    }

    private void validaLoginExistente(String login) {
        if (usuarioGateway.existePorLogin(login))
            throw new LoginJaCadastradoException(login);
    }
}
