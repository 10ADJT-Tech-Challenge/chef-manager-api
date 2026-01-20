
package com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.UUID;

public record CadastrarItemCardapioInput(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @Valid @DecimalMin(value = "0.01") BigDecimal preco,
        boolean consumoLocal,
        String caminhoFoto,
        @NotNull UUID restauranteId
) { }
