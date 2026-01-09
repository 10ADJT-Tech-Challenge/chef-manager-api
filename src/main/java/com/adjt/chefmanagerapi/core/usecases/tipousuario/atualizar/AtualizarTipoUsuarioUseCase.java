package com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoEncontradoException;
import com.adjt.chefmanagerapi.core.exceptions.TipoUsuarioNaoPodeTrocarCategoriaException;
import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.TipoUsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AtualizarTipoUsuarioUseCase implements AtualizarTipoUsuario {
    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final TipoUsuarioMapper mapper;

    public AtualizarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway, TipoUsuarioMapper mapper) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.mapper = mapper;
    }

    @Override
    public AtualizarTipoUsuarioOutput executar(AtualizarTipoUsuarioInput input) {
        TipoUsuario tipoUsuarioExistente = getTipoUsuarioExistenteOuLancaErro(input);
        atualizarDadosUsuario(tipoUsuarioExistente, input);

        return mapper.toAtualizarTipoUsuarioOutput(tipoUsuarioGateway.salvar(tipoUsuarioExistente));
    }

    private void atualizarDadosUsuario(TipoUsuario tipoUsuario, AtualizarTipoUsuarioInput dto) {
        if (!Objects.equals(dto.categoriaUsuario(), tipoUsuario.getCategoriaUsuario().name()))
            throw new TipoUsuarioNaoPodeTrocarCategoriaException();

        atualizaNomeSePresenteEValido(tipoUsuario, dto.nome());
    }

    private void atualizaNomeSePresenteEValido(TipoUsuario tipoUsuario, String nome) {
        Optional.ofNullable(nome).ifPresent(tipoUsuario::atualizarNome);
    }

    private TipoUsuario getTipoUsuarioExistenteOuLancaErro(AtualizarTipoUsuarioInput input) {
        return tipoUsuarioGateway.buscaPorId(input.id())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(input.id()));
    }
}
