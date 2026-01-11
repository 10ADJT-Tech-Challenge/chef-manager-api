package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RestaurantePersistenceMapper {

    @Mapping(target = "responsavelId", source = "responsavelId")
    @Mapping(target = "responsavel", ignore = true)
    RestauranteEntity toEntity(RestauranteGatewayDto restaurante);

    @Mappings({
            @Mapping(target = "responsavel.endereco.rua", expression = "java(usuarioEntity.getRua())"),
            @Mapping(target = "responsavel.endereco.numero", expression = "java(usuarioEntity.getNumero())"),
            @Mapping(target = "responsavel.endereco.cidade", expression = "java(usuarioEntity.getCidade())"),
            @Mapping(target = "responsavel.endereco.cep", expression = "java(usuarioEntity.getCep())"),
            @Mapping(target = "responsavel.endereco.uf", expression = "java(usuarioEntity.getUf())")
    })
    RestauranteGatewayDto toDomain(RestauranteEntity restaurante);
}
