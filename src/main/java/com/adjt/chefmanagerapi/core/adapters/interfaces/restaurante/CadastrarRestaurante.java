package com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante;

import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.CadastrarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;

public interface CadastrarRestaurante {
    RestauranteOutput executar(CadastrarRestauranteInput input);
}
