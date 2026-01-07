package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurante")
public class RestauranteEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipoCozinha;

    @Column(nullable = false)
    private String horarioFuncionamento;

    private UUID responsavel;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private OffsetDateTime dataUltimaAlteracao;
}


