
package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
public class CadastrarItemCardapioUseCase implements CadastrarItemCardapio {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;
    private final RestauranteGateway restauranteGateway;

    public CadastrarItemCardapioUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper, RestauranteGateway restauranteGateway) {
        this.gateway = gateway;
        this.mapper = mapper;
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public ItemCardapioOutput executar(CadastrarItemCardapioInput input) {
        if (!restauranteGateway.existePorId(input.restauranteId())) {
            throw new NoSuchElementException("Nenhum restaurante encontrado com o id: " + input.restauranteId());
        }

        validar(input);

        ItemCardapio novo = new ItemCardapio(
                input.nome(),
                input.descricao(),
                input.preco(),
                input.consumoLocal(),
                input.caminhoFoto(),
                input.restauranteId()
        );
        var salvo = gateway.salvar(novo);
        return mapper.toOutput(salvo);
    }


    private void validar(CadastrarItemCardapioInput input) {
        if (input.nome() == null || input.nome().isBlank()) {
            throw new IllegalArgumentException("Nome do item é obrigatório.");
        }
        if (input.preco().signum() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
    }


}
