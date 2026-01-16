
package com.adjt.chefmanagerapi.core.usecases.cardapio.deletar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoverItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public RemoverItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public void executar(UUID id) {
        if (!repository.existePorId(id)) {
            throw new IllegalArgumentException("Item do cardápio não encontrado: " + id);
        }
        repository.remover(id);
    }
}
