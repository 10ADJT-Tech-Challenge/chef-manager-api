
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarItensCardapioPorNomeUseCase implements BuscarItensCardapioPorNome {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public BuscarItensCardapioPorNomeUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public List<ItemCardapioOutput> executar(String termo) {
        var itens = gateway.buscarPorNome(termo);
        return mapper.toOutputList(itens);
    }
}
