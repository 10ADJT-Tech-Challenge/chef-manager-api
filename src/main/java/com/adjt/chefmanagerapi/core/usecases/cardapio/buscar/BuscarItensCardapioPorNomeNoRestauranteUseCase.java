
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarItensCardapioPorNomeNoRestauranteUseCase implements BuscarItensCardapioPorNomeNoRestaurante {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public BuscarItensCardapioPorNomeNoRestauranteUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public List<ItemCardapioOutput> executar(BuscarItensCardapioPorNomeNoRestauranteInput input) {
        var itens = gateway.buscarPorNomeNoRestaurante(input.restauranteId(), input.termo());
        return mapper.toOutputList(itens);
    }
}