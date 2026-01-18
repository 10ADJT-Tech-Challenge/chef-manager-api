
package com.adjt.chefmanagerapi.core.gateways.cardapio;

import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.interfaces.ItemCardapioRepositoryGateway;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioPersistenceMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemCardapioGatewayImpl implements ItemCardapioGateway {

    private final ItemCardapioRepositoryGateway repository;

    public ItemCardapioGatewayImpl(ItemCardapioRepositoryGateway repository, ItemCardapioPersistenceMapper mapper) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        var dto = ItemCardapioGatewayMapper.toDto(item);
        var salvo = repository.salvar(dto);
        return ItemCardapioGatewayMapper.toDomain(salvo);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(UUID id) {
        return repository.buscarPorId(id).map(ItemCardapioGatewayMapper::toDomain);
    }

    @Override
    public List<ItemCardapio> findAllByIdRestauranteOrderByNomeAsc(UUID idRestaurante) {
        return repository.findAllByIdRestauranteOrderByNomeAsc(idRestaurante)
                .stream()
                .map(ItemCardapioGatewayMapper::toDomain)
                .toList();
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio item) {
        var dto = ItemCardapioGatewayMapper.toDto(item);
        var atualizado = repository.atualizar(dto);
        return ItemCardapioGatewayMapper.toDomain(atualizado);
    }

    @Override
    public void remover(UUID id) {
        repository.remover(id);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repository.existePorId(id);
    }
}
