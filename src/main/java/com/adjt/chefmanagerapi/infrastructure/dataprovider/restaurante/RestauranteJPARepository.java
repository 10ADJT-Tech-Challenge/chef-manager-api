package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteJPARepository extends JpaRepository<RestauranteEntity, UUID> {
    @Query("SELECT r FROM RestauranteEntity r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Optional<RestauranteEntity> findByNome(@Param("nome") String nome);

    void deleteById(@NonNull UUID id);

    void deleteAll();

    boolean existsById(@NonNull UUID id);
}
