
package com.adjt.chefmanagerapi.core.usecases.cardapio.remover;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RemoverItemCardapioUseCase implements RemoverItemCardapio {

    private final ItemCardapioGateway gateway;

    public RemoverItemCardapioUseCase(ItemCardapioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Void executar(UUID id) {
        if (!gateway.existePorId(id)) {
            throw new NoSuchElementException("Nenhum item do cardápio encontrado com o id: " + id);
        }
        gateway.remover(id);
        return null;
    }
}
