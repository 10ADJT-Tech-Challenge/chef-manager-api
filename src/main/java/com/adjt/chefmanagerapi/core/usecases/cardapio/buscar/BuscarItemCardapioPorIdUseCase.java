
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway repository;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Item do cardápio não encontrado: " + id));
    }
}
