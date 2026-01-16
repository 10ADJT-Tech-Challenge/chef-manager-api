
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;

import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarItensCardapioPorIdRestauranteUseCase {

    private final ItemCardapioGateway repository;
    private final ItemCardapioMapper mapper;

    public ListarItensCardapioPorIdRestauranteUseCase(ItemCardapioGateway repository, ItemCardapioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ItemCardapioOutput> executar(UUID idRestaurante) {
        var itens = repository.findAllByIdRestauranteOrderByNomeAsc(idRestaurante);
        return mapper.toOutputList(itens);
    }






}
