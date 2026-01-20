package com.adjt.chefmanagerapi.infrastructure.api.controller.cardapio;
import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import com.adjt.chefmanagerapi.model.ItemCardapioResponse;

public class ItemCardapioApiMapper {

    public static ItemCardapioResponse toResponse(ItemCardapioOutput domain) {
        ItemCardapioResponse dto = new ItemCardapioResponse();
        dto.setId(domain.getId());
        dto.setNome(domain.getNome());
        dto.setDescricao(domain.getDescricao());
        dto.setPreco(domain.getPreco());
        dto.setConsumoLocal(domain.isConsumoLocal());
        dto.setCaminhoFoto(domain.getCaminhoFoto());
        dto.setRestauranteId(domain.getRestauranteId());
        dto.setDataUltimaAlteracao(domain.getDataUltimaAlteracao());
        return dto;
    }
    public static ItemCardapioResponse toResponseFromOutput(ItemCardapioOutput domain) {
        ItemCardapioResponse dto = new ItemCardapioResponse();
        dto.setId(domain.getId());
        dto.setNome(domain.getNome());
        dto.setDescricao(domain.getDescricao());
        dto.setPreco(domain.getPreco());
        dto.setConsumoLocal(domain.isConsumoLocal());
        dto.setCaminhoFoto(domain.getCaminhoFoto());
        dto.setRestauranteId(domain.getRestauranteId());
        dto.setDataUltimaAlteracao(domain.getDataUltimaAlteracao());
        return dto;
    }

    // Se você tiver um DTO de criação/atualização gerado pelo OpenAPI (ex.: ItemCardapioCreate/Update),
    // crie métodos fromRequest(...) que construam o domínio usando o construtor "de criação".
}
