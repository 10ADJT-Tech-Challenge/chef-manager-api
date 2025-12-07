package com.adjt.chefmanagerapi.core.adapters.mappers;

import com.adjt.chefmanagerapi.core.domain.dtos.usuario.UsuarioOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    UsuarioOutput toOutput(Usuario usuario);
}
