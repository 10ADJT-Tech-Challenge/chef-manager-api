
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.usecases.common.UseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import java.util.UUID;

public interface BuscarItemCardapioPorId extends UseCase<UUID, ItemCardapioOutput> { }