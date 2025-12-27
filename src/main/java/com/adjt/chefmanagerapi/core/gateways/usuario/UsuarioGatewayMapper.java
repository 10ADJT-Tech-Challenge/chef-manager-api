package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioGatewayMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    UsuarioGatewayDTO toGatewayDTO(Usuario usuario);

    Usuario toDomain(UsuarioGatewayDTO usuarioGatewayDTO);
}
