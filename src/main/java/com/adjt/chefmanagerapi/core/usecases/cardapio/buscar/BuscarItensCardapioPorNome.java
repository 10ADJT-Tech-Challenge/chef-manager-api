
package com.adjt.chefmanagerapi.core.usecases.cardapio.buscar;

import com.adjt.chefmanagerapi.core.usecases.common.UseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;

import java.util.List;

public interface BuscarItensCardapioPorNome extends UseCase<String, List<ItemCardapioOutput>> { }