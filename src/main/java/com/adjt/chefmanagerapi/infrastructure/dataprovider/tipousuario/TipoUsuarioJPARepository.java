package com.adjt.chefmanagerapi.infrastructure.dataprovider.tipousuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoUsuarioJPARepository extends JpaRepository<TipoUsuarioEntity, UUID> {

    boolean existsByNome(String nome);
}
