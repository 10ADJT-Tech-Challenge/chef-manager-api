package com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.AtualizarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;

import java.util.UUID;

public interface AtualizarRestaurante {
    RestauranteOutput executar(UUID id, AtualizarRestauranteInput input);
}
