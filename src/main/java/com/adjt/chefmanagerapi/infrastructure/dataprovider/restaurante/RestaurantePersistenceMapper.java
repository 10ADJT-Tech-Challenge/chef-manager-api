package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayDto;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario.UsuarioPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UsuarioPersistenceMapper.class)
public interface RestaurantePersistenceMapper {

    @Mapping(target = "responsavelId", source = "responsavelId")
    @Mapping(target = "responsavel", ignore = true)
    RestauranteEntity toEntity(RestauranteGatewayDto restaurante);

    @Mapping(target = "responsavelId", source = "responsavelId")
    @Mapping(target = "responsavel", source = "responsavel")
    RestauranteGatewayDto toDomain(RestauranteEntity restaurante);
}
