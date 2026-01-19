
package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayDto;
import com.adjt.chefmanagerapi.core.gateways.interfaces.ItemCardapioRepositoryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemCardapioJPARepositoryGateway implements ItemCardapioRepositoryGateway {

    private final ItemCardapioJPARepository repo;
    private final ItemCardapioPersistenceMapper mapper;

    public ItemCardapioJPARepositoryGateway(ItemCardapioJPARepository repo,
                                            ItemCardapioPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapioGatewayDto salvar(ItemCardapioGatewayDto dto) {
        var e = mapper.toEntity(dto);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Override
    public Optional<ItemCardapioGatewayDto> buscarPorId(UUID id) {
        return repo.findById(id).map(mapper::toDto);
    }


    @Override
    public List<ItemCardapioGatewayDto> buscarPorNome(String termo) {
        return repo.findByNomeContainingIgnoreCaseOrderByNomeAsc(termo)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ItemCardapioGatewayDto> buscarPorNomeNoRestaurante(UUID restauranteId, String termo) {
        return repo.findByRestauranteIdAndNomeContainingIgnoreCaseOrderByNomeAsc(restauranteId, termo)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Override
    public List<ItemCardapioGatewayDto> findAllByRestauranteIdOrderByNomeAsc(UUID restauranteId) {
        return repo.findAllByRestauranteIdOrderByNomeAsc(restauranteId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ItemCardapioGatewayDto atualizar(ItemCardapioGatewayDto dto) {
        return salvar(dto);
    }

    @Override
    public void remover(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repo.existsById(id);
    }
}
