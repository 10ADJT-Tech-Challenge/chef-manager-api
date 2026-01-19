package com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "itens_cardapio")
@Getter @Setter
public class ItemCardapioEntity {
    @Id
    private UUID id;

    private String nome;
    private String descricao;

    @Column(precision = 12, scale = 2)
    private BigDecimal preco;

    private boolean consumoLocal;
    private String caminhoFoto;
    private UUID idRestaurante;

    private OffsetDateTime dataUltimaAlteracao;
}
