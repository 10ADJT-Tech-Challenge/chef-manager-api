package com.adjt.chefmanagerapi.infrastructure.api.mapper;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.AtualizarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.CadastrarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.Restaurante;
import com.adjt.chefmanagerapi.model.RestauranteRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteApiMapper {

    AtualizarRestauranteInput toInput(AtualizarRestauranteRequest restaurante);

    CadastrarRestauranteInput toInput(RestauranteRequest request);

    Restaurante toResponse(RestauranteOutput restaurante);
}