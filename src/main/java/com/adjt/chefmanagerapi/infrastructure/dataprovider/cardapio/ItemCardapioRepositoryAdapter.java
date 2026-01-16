package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;


import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioJPARepository;
import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemCardapioRepositoryAdapter implements ItemCardapioGateway {

    private final ItemCardapioJPARepository jpa;

    public ItemCardapioRepositoryAdapter(ItemCardapioJPARepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        var saved = jpa.save(ItemCardapioPersistenceMapper.toEntity(item));
        return ItemCardapioPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(UUID id) {
        return jpa.findById(id).map(ItemCardapioPersistenceMapper::toDomain);
    }

    @Override
    public List<ItemCardapio> findAllByIdRestauranteOrderByNomeAsc(UUID idRestaurante) {
        return jpa.findAll().stream().map(ItemCardapioPersistenceMapper::toDomain).toList();
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio item) {
        var saved = jpa.save(ItemCardapioPersistenceMapper.toEntity(item));
        return ItemCardapioPersistenceMapper.toDomain(saved);
    }

    @Override
    public void remover(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existePorId(UUID id) {
        return jpa.existsById(id);
    }
}

