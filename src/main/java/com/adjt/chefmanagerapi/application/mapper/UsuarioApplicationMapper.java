package com.adjt.chefmanagerapi.application.mapper;

import com.adjt.chefmanagerapi.application.gateway.inbound.dto.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioApplicationMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    UsuarioOutput toOutput(Usuario usuario);
}
