package com.adjt.chefmanagerapi.infrastructure.dataprovider.mapper;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantePersistenceMapper {

    @Mapping(target = "responsavel", source = "responsavel.id")
    RestauranteEntity toEntity(Restaurante restaurante);

    @Mapping(target = "responsavel", ignore = true)
    Restaurante toDomain(RestauranteEntity entity);
}
