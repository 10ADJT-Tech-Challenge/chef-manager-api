
package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CadastrarItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public CadastrarItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(String nome, String descricao, BigDecimal preco,
                                 boolean apenasNoRestaurante, String caminhoFoto, UUID idRestaurante) {
        ItemCardapio item = new ItemCardapio(nome, descricao, preco, apenasNoRestaurante, caminhoFoto,idRestaurante);
        return repository.salvar(item);
    }
}
