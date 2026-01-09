package com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Optional<UsuarioEntity> findByNome(@Param("nome") String nome);

    void deleteById(@NonNull UUID id);

    void deleteAll();

    boolean existsById(@NonNull UUID id);

    boolean existsByEmail(@NonNull String email);

    boolean existsByLogin(@NonNull String login);

    Optional<UsuarioEntity> findByLogin(String login);

    boolean existsByTipoUsuarioId(UUID tipoUsuarioId);
}
