
package com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioMapper;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AtualizarItemCardapioUseCase implements AtualizarItemCardapio {

    private final ItemCardapioGateway gateway;
    private final ItemCardapioMapper mapper;

    public AtualizarItemCardapioUseCase(ItemCardapioGateway gateway, ItemCardapioMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapioOutput executar(AtualizarItemCardapioInput input) {
        UUID id = input.id();
        ItemCardapio item = gateway.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum item do cardápio encontrado com o id: " + id));

        if (input.nome() != null) item.atualizarNome(input.nome());
        if (input.descricao() != null) item.atualizarDescricao(input.descricao());
        if (input.preco() != null) item.atualizarPreco(input.preco());
        if (input.consumoLocal() != null) item.atualizarDisponibilidade(input.consumoLocal());
        if (input.caminhoFoto() != null) item.atualizarCaminhoFoto(input.caminhoFoto());

        ItemCardapio atualizado = gateway.atualizar(item);
        return mapper.toOutput(atualizado);
    }
}