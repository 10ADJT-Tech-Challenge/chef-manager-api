
package com.adjt.chefmanagerapi.core.gateways.cardapio;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemCardapioGateway {
    ItemCardapio salvar(ItemCardapio item);
    Optional<ItemCardapio> buscarPorId(UUID id);
    List<ItemCardapio> findAllByRestauranteIdOrderByNomeAsc(UUID restauranteId);
    ItemCardapio atualizar(ItemCardapio item);
    void remover(UUID id);
    boolean existePorId(UUID id);
    List<ItemCardapio> buscarPorNome(String termo);
    List<ItemCardapio> buscarPorNomeNoRestaurante(UUID restauranteId, String termo);

}
