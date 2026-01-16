package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioEntity;

public class ItemCardapioPersistenceMapper {

    public static ItemCardapioEntity toEntity(ItemCardapio d) {
        ItemCardapioEntity e = new ItemCardapioEntity();
        e.setId(d.getId());
        e.setNome(d.getNome());
        e.setDescricao(d.getDescricao());
        e.setPreco(d.getPreco());
        e.setApenasNoRestaurante(d.isConsumoLocal());
        e.setCaminhoFoto(d.getCaminhoFoto());
        e.setIdRestaurante(d.getIdRestaurante());
        e.setDataUltimaAlteracao(d.getDataUltimaAlteracao());
        return e;
    }

    public static ItemCardapio toDomain(ItemCardapioEntity e) {
        return new ItemCardapio(
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

