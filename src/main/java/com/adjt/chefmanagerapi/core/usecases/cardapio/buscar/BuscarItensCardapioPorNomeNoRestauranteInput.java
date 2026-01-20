package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import java.util.UUID;

public record BuscarItensCardapioPorNomeNoRestauranteInput(UUID restauranteId, String termo) { }

