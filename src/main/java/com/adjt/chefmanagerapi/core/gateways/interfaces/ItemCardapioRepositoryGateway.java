
package com.adjt.chefmanagerapi.core.gateways.interfaces;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemCardapioRepositoryGateway {
    ItemCardapioGatewayDto salvar(ItemCardapioGatewayDto dto);
    Optional<ItemCardapioGatewayDto> buscarPorId(UUID id);
       List<ItemCardapioGatewayDto> findAllByIdRestauranteOrderByNomeAsc(UUID idRestaurante);
    ItemCardapioGatewayDto atualizar(ItemCardapioGatewayDto dto);
    void remover(UUID id);
    boolean existePorId(UUID id);
    List<ItemCardapioGatewayDto> buscarPorNome(String termo);
    List<ItemCardapioGatewayDto> buscarPorNomeNoRestaurante(UUID idRestaurante, String termo);

}