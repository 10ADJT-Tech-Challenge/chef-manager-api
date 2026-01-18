
package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayDto;
import org.springframework.stereotype.Component;

@Component
public class ItemCardapioPersistenceMapper {

    public ItemCardapioEntity toEntity(ItemCardapioGatewayDto dto) {
        ItemCardapioEntity e = new ItemCardapioEntity();
        e.setId(dto.id());
        e.setNome(dto.nome());
        e.setDescricao(dto.descricao());
        e.setPreco(dto.preco());
        e.setApenasNoRestaurante(dto.consumoLocal());
        e.setCaminhoFoto(dto.caminhoFoto());
        e.setIdRestaurante(dto.idRestaurante());
        e.setDataUltimaAlteracao(dto.dataUltimaAlteracao());
        return e;
    }

    public ItemCardapioGatewayDto toDto(ItemCardapioEntity e) {
        return new ItemCardapioGatewayDto(
                e.getId(),
                e.getNome(),
                e.getDescricao(),
                e.getPreco(),
                e.isApenasNoRestaurante(),
                e.getCaminhoFoto(),
                e.getIdRestaurante(),
                e.getDataUltimaAlteracao()
        );
    }
}
