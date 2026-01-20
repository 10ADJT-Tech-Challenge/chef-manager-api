package com.adjt.chefmanagerapi.core.usecases.cardapio;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCardapioMapper {
    ItemCardapioOutput toOutput(ItemCardapio item);
    List<ItemCardapioOutput> toOutputList(List<ItemCardapio> itens);
}
