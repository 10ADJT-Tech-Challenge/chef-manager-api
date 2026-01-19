
package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Input para o caso de uso de cadastro de Item do Cardápio.
 * Campos alinhados com o OpenAPI (consumoLocal).
 */
public record CadastrarItemCardapioInput(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @Valid @DecimalMin(value = "0.01") BigDecimal preco,
        @NotNull Boolean consumoLocal,
        String caminhoFoto,
        @NotNull UUID restauranteId
) { }
