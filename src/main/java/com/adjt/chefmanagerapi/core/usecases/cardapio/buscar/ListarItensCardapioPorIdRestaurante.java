package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.usecases.common.UseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import java.util.List;
import java.util.UUID;

public interface ListarItensCardapioPorIdRestaurante extends UseCase<UUID, List<ItemCardapioOutput>> { }
