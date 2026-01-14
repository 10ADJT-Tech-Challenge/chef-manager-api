package com.adjt.chefmanagerapi.infrastructure.api.controller.restaurante;

import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar.AtualizarRestauranteInput;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar.CadastrarRestauranteInput;
import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestauranteApiMapper {

    @Mapping(target = "id", source = "id")
    AtualizarRestauranteInput toAtualizarInput(UUID id, AtualizarRestauranteRequest restaurante);

    @Mapping(target = "responsavel", source = "idDono")
    CadastrarRestauranteInput toCadastrarInput(RestauranteRequest request);

    @Mapping(target = "idDono", source = "responsavel")
    RestauranteResponse toResponse(RestauranteOutput restaurante);
}