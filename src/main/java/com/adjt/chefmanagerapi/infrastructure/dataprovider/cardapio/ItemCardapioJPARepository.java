
package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemCardapioJPARepository extends JpaRepository<ItemCardapioEntity, UUID> {
    List<ItemCardapioEntity> findAllByIdRestauranteOrderByNomeAsc(UUID idRestaurante);
    List<ItemCardapioEntity> findByNomeContainingIgnoreCaseOrderByNomeAsc(String termo);
    List<ItemCardapioEntity> findByIdRestauranteAndNomeContainingIgnoreCaseOrderByNomeAsc(UUID idRestaurante, String termo);

}
