
package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCardapioJPARepository extends JpaRepository<ItemCardapioEntity, UUID> {
    List<ItemCardapioEntity> findAllByRestauranteIdOrderByNomeAsc(UUID restauranteId);
    List<ItemCardapioEntity> findByNomeContainingIgnoreCaseOrderByNomeAsc(String termo);
    List<ItemCardapioEntity> findByRestauranteIdAndNomeContainingIgnoreCaseOrderByNomeAsc(UUID restauranteId, String termo);

}
