package com.adjt.chefmanagerapi.core.gateways.tipousuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoUsuarioGatewayMapper {

    @Mapping(target = "categoriaUsuario", expression = "java(tipoUsuario.getCategoriaUsuario().toString())")
    TipoUsuarioGatewayDTO toGatewayDTO(TipoUsuario tipoUsuario);

    default TipoUsuario toDomain(TipoUsuarioGatewayDTO tipoUsuarioGatewayDTO) {
        return new TipoUsuario(
                tipoUsuarioGatewayDTO.id(),
                tipoUsuarioGatewayDTO.nome(),
                CategoriaUsuario.valueOf(tipoUsuarioGatewayDTO.categoriaUsuario()));
    }
}
