
package com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AtualizarItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public AtualizarItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(UUID id, String nome, String descricao, BigDecimal preco,
                                 Boolean consumoLocal, String caminhoFoto,UUID idRestaurante) {
        ItemCardapio item = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Item do cardápio não encontrado: " + id));

        if (nome != null) item.atualizarNome(nome);
        if (descricao != null) item.atualizarDescricao(descricao);
        if (preco != null) item.atualizarPreco(preco);
        if (consumoLocal != null) item.atualizarDisponibilidade(consumoLocal);
        if (caminhoFoto != null) item.atualizarCaminhoFoto(caminhoFoto);
        if (idRestaurante != null) item.atualizarIdRestaurante(idRestaurante);
        return repository.atualizar(item);
    }
}
