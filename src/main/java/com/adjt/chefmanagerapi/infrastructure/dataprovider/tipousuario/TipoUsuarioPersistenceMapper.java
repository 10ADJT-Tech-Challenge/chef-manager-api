package com.adjt.chefmanagerapi.infrastructure.dataprovider.tipousuario;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGatewayDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoUsuarioPersistenceMapper {
    TipoUsuarioEntity toEntity(TipoUsuarioGatewayDTO tipoUsuario);

    TipoUsuarioGatewayDTO toDto(TipoUsuarioEntity tipoUsuarioEntity);
}
