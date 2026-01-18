
package com.adjt.chefmanagerapi.core.gateways.cardapio;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ItemCardapioGatewayDto(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean consumoLocal,
        String caminhoFoto,
        UUID idRestaurante,
        OffsetDateTime dataUltimaAlteracao
) {}
