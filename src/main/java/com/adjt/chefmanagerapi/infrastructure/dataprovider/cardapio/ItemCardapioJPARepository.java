
package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCardapioJPARepository extends JpaRepository<ItemCardapioEntity, UUID> {
    List<ItemCardapioEntity> findAllByIdRestauranteOrderByNomeAsc(UUID idRestaurante);
}
