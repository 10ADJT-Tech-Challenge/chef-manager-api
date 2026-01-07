package com.adjt.chefmanagerapi.core.adapters.mappers;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteOutput toOutput(Restaurante restaurante);
}
