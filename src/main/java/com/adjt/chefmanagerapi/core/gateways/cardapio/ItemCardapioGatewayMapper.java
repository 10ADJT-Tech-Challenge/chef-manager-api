
package com.adjt.chefmanagerapi.core.gateways.cardapio;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;

public final class ItemCardapioGatewayMapper {

    private ItemCardapioGatewayMapper() {}

    public static ItemCardapioGatewayDto toDto(ItemCardapio d) {
        if (d == null) return null;
        return new ItemCardapioGatewayDto(
                d.getId(),
                d.getNome(),
                d.getDescricao(),
                d.getPreco(),
                d.isConsumoLocal(),
                d.getCaminhoFoto(),
                d.getRestauranteId(),
                d.getDataUltimaAlteracao()
        );
    }

    public static ItemCardapio toDomain(ItemCardapioGatewayDto dto) {
        if (dto == null) return null;
        // Usa o construtor de reidratação do domínio (com id e dataUltimaAlteracao)
        return new ItemCardapio(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.consumoLocal(),
                dto.caminhoFoto(),
                dto.restauranteId(),
                dto.dataUltimaAlteracao()
        );
    }
}
