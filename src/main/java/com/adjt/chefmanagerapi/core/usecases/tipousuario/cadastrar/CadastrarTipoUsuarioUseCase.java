package com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioJaCadastradoException;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapper;
import org.springframework.stereotype.Service;

@Service
public class CadastrarTipoUsuarioUseCase implements CadastrarTipoUsuario {
    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final TipoUsuarioMapper mapper;

    public CadastrarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway, TipoUsuarioMapper mapper) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public CadastrarTipoUsuarioOutput executar(CadastrarTipoUsuarioInput input) {
        if (tipoUsuarioGateway.isExisteComNome(input.nome()))
            throw new TipoUsuarioJaCadastradoException(input.nome());

        TipoUsuario tipoUsuario = new TipoUsuario(input.nome(), CategoriaUsuario.valueOf(input.categoriaUsuario()));
        return mapper.toCadastrarTipoUsuarioOutput(tipoUsuarioGateway.salvar(tipoUsuario));
    }
}
