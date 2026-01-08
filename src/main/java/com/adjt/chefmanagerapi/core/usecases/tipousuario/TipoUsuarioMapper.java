package com.adjt.chefmanagerapi.core.usecases.tipousuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {
    CadastrarTipoUsuarioOutput toCadastrarTipoUsuarioOutput(TipoUsuario tipoUsuario);

    AtualizarTipoUsuarioOutput toAtualizarTipoUsuarioOutput(TipoUsuario tipoUsuario);

    BuscarTipoUsuarioOutput toBuscarTipoUsuarioOutput(TipoUsuario tipoUsuario);
}
