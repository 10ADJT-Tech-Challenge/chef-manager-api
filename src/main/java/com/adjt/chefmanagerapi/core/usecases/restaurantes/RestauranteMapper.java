package com.adjt.chefmanagerapi.core.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mapping(target = "responsavel", expression = "java(restaurante.getResponsavel().getId())")
    RestauranteOutput toOutput(Restaurante restaurante);
}
