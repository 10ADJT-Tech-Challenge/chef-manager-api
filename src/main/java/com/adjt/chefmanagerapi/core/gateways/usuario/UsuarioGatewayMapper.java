package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioGatewayMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    UsuarioGatewayDTO toGatewayDTO(Usuario usuario);

    @Mapping(target = "tipo", expression = "java(com.adjt.chefmanagerapi.core.domain.valueobjects.TipoUsuario.valueOf(usuarioGatewayDTO.tipo()))")
    Usuario toDomain(UsuarioGatewayDTO usuarioGatewayDTO);
}
