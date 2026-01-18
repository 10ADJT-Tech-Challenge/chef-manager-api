
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;

import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarItensCardapioPorIdRestauranteUseCase {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public ListarItensCardapioPorIdRestauranteUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    public List<ItemCardapioOutput> executar(UUID idRestaurante) {
        var itens = gateway.findAllByIdRestauranteOrderByNomeAsc(idRestaurante);
        return mapper.toOutputList(itens);
    }






}
