package com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante;

import com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario.UsuarioEntity;
import jakarta.persistence.*;
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

    @Column(name = "responsavel_id", insertable = false, updatable = false)
    private UUID responsavelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsavel_id")
    private UsuarioEntity responsavel;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private OffsetDateTime dataUltimaAlteracao;
}


