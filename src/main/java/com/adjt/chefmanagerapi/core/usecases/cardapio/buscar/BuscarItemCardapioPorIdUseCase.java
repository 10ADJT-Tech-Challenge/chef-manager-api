
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarItemCardapioPorIdUseCase implements BuscarItemCardapioPorId {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapioOutput executar(UUID id) {
        Optional<ItemCardapio> opt = gateway.buscarPorId(id);
        if (opt.isEmpty()) {
            throw new NoSuchElementException("Nenhum item do cardápio encontrado com o id: " + id);
        }
        return mapper.toOutput(opt.get());
    }
}
