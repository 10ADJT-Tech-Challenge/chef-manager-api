
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;

import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarItensCardapioPorRestauranteIdUseCase implements ListarItensCardapioPorRestauranteId {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public ListarItensCardapioPorRestauranteIdUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    public List<ItemCardapioOutput> executar(UUID restauranteId) {
        var itens = gateway.findAllByRestauranteIdOrderByNomeAsc(restauranteId);
        return mapper.toOutputList(itens);
    }






}
