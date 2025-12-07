package com.adjt.chefmanagerapi.infrastructure.api.mapper;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.AlterarSenhaInput;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.AtualizarUsuarioInput;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.CadastrarUsuarioInput;
import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.model.AlterarSenhaRequest;
import com.adjt.chefmanagerapi.model.AtualizarUsuarioRequest;
import com.adjt.chefmanagerapi.model.Usuario;
import com.adjt.chefmanagerapi.model.UsuarioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioApiMapper {

    @Mapping(target = "usuarioId", source = "id")
    AlterarSenhaInput toInput(UUID id, AlterarSenhaRequest request);

    AtualizarUsuarioInput toInput(AtualizarUsuarioRequest request);

    CadastrarUsuarioInput toInput(UsuarioRequest generatedRequest);

    Usuario toResponse(UsuarioOutput usuario);

}