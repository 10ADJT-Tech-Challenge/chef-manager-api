package com.adjt.chefmanagerapi.infrastructure.api.controller.usuario;

import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenhaInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioOutput;
import com.adjt.chefmanagerapi.model.AlterarSenhaRequest;
import com.adjt.chefmanagerapi.model.AtualizarUsuarioRequest;
import com.adjt.chefmanagerapi.model.UsuarioRequest;
import com.adjt.chefmanagerapi.model.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioApiMapper {

    @Mapping(target = "usuarioId", source = "id")
    AlterarSenhaInput toInput(UUID id, AlterarSenhaRequest request);

    @Mapping(target = "id", source = "id")
    AtualizarUsuarioInput toInput(UUID id, AtualizarUsuarioRequest request);

    CadastrarUsuarioInput toInput(UsuarioRequest generatedRequest);

    UsuarioResponse toResponse(CadastrarUsuarioOutput usuario);

    UsuarioResponse toResponse(AtualizarUsuarioOutput usuario);

    UsuarioResponse toResponse(BuscarUsuarioOutput usuario);

}