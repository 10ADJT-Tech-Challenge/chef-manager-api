package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import com.adjt.chefmanagerapi.infrastructure.dataprovider.restaurante.RestauranteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "itens_cardapio")
@NoArgsConstructor
@Getter
@Setter
public class ItemCardapioEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(precision = 12, scale = 2,nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private boolean consumoLocal;


    private String caminhoFoto;

    @Column(name = "restaurante_id", nullable = false)
    private UUID restauranteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", insertable = false, updatable = false)
    private RestauranteEntity restaurante;

    private OffsetDateTime dataUltimaAlteracao;
}
