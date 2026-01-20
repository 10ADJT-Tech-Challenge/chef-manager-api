package com.adjt.chefmanagerapi.infraestructure.api.controller.restaurante;

import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteRequest;

import java.util.UUID;

public abstract class RestauranteRequestHelper {

    static final UUID UUID_RESPONSAVEL = UUID.fromString("e98915ad-2a2f-4463-ac5f-38258d3ffa5d");
    static final String UUID_CAMAROES = "cec64cf0-6dc9-4b4e-b0b8-405870ae1b43";
    static final String UUID_BRAZ_ELETTRICA = "f6f2a623-b22b-4494-bb35-cff956c86e5c";

    static RestauranteRequest getRestauranteRequest() {
        return new RestauranteRequest()
                .nome("Camarões")
                .endereco("Rua Principal")
                .tipoCozinha("Nordestina")
                .horarioFuncionamento("10h às 22h")
                .idDono(UUID_RESPONSAVEL);
    }

    static AtualizarRestauranteRequest getAtualizarRestauranteRequest() {
        return new AtualizarRestauranteRequest()
                .nome("Braz Elettrica")
                .endereco("Av. Paulista")
                .tipoCozinha("Italiana")
                .horarioFuncionamento("10h às 22h")
                .idDono(UUID_RESPONSAVEL);
    }
}
