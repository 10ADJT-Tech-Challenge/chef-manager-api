package com.adjt.chefmanagerapi.infrastructure.api.controller.tipousuario;

import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.model.TipoUsuarioRequest;
import com.adjt.chefmanagerapi.model.TipoUsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TipoUsuarioApiMapper {

    TipoUsuarioResponse toResponse(CadastrarTipoUsuarioOutput tipoUsuarioApiDTO);

    CadastrarTipoUsuarioInput toInput(TipoUsuarioRequest tipoUsuarioRequest);

    @Mapping(target = "id", source = "id")
    AtualizarTipoUsuarioInput toInput(UUID id, TipoUsuarioRequest tipoUsuarioRequest);

    TipoUsuarioResponse toResponse(AtualizarTipoUsuarioOutput usuario);

    TipoUsuarioResponse toResponse(BuscarTipoUsuarioOutput tipoUsuario);
}
