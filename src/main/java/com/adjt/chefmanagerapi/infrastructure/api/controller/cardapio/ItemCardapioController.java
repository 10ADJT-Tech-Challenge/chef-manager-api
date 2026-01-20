package com.adjt.chefmanagerapi.infrastructure.api.controller.cardapio;

import com.adjt.chefmanagerapi.ItemCardapioApi;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar.AtualizarItemCardapio;
import com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar.AtualizarItemCardapioInput;
import com.adjt.chefmanagerapi.core.usecases.cardapio.buscar.*;
import com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar.CadastrarItemCardapio;
import com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar.CadastrarItemCardapioInput;
import com.adjt.chefmanagerapi.core.usecases.cardapio.remover.RemoverItemCardapio;
import com.adjt.chefmanagerapi.model.ItemCardapioRequest;
import com.adjt.chefmanagerapi.model.ItemCardapioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
public class ItemCardapioController implements ItemCardapioApi {

    private final CadastrarItemCardapio criarUC;
    private final AtualizarItemCardapio atualizarUC;
    private final RemoverItemCardapio removerUC;
    private final BuscarItemCardapioPorId buscarUC;
    private final ListarItensCardapioPorRestauranteId listarPorRestauranteIdUC;
    private final BuscarItensCardapioPorNomeNoRestaurante listarNoRestaurantePorNomeUC;
    private final BuscarItensCardapioPorNome buscarItensCardapioPorNome;

    public ItemCardapioController(CadastrarItemCardapio criarUC,
                                  AtualizarItemCardapio atualizarUC,
                                  RemoverItemCardapio removerUC,
                                  BuscarItemCardapioPorId buscarUC,
                                  ListarItensCardapioPorRestauranteId listarPorRestauranteIdUC, BuscarItensCardapioPorNomeNoRestaurante listarNoRestaurantePorNomeUC, BuscarItensCardapioPorNome buscarItensCardapioPorNome) {
        this.criarUC = criarUC;
        this.atualizarUC = atualizarUC;
        this.removerUC = removerUC;
        this.buscarUC = buscarUC;
        this.listarPorRestauranteIdUC = listarPorRestauranteIdUC;
        this.listarNoRestaurantePorNomeUC = listarNoRestaurantePorNomeUC;
        this.buscarItensCardapioPorNome = buscarItensCardapioPorNome;
    }

    @Override
    public ResponseEntity<ItemCardapioResponse> criarItemCardapio(UUID id, ItemCardapioRequest req) {
        CadastrarItemCardapioInput input = new CadastrarItemCardapioInput(
                req.getNome(),
                req.getDescricao(),
                req.getPreco(),
                req.getConsumoLocal(),
                req.getCaminhoFoto(),
                id);

        ItemCardapioOutput item = criarUC.executar(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemCardapioApiMapper.toResponse(item));
    }

    @Override
    public ResponseEntity<ItemCardapioResponse> buscarItemCardapioPorId(UUID id) {
        ItemCardapioOutput item = buscarUC.executar(id);
        return ResponseEntity.ok(ItemCardapioApiMapper.toResponse(item));
    }


    @Override
    public ResponseEntity<List<ItemCardapioResponse>> buscarItensCardapioPorRestauranteId(
            UUID restauranteId,
            String termo
    ) {
        List<ItemCardapioOutput> outputs;

        if (termo == null || termo.isBlank()) {
            outputs = listarPorRestauranteIdUC.executar(restauranteId);
        } else {
            var input = new BuscarItensCardapioPorNomeNoRestauranteInput(restauranteId, termo);
            outputs = listarNoRestaurantePorNomeUC.executar(input);
        }

        var body = outputs.stream().map(ItemCardapioApiMapper::toResponseFromOutput).toList();
        if (body.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<List<ItemCardapioResponse>> buscarItensCardapioPorNomeGlobal(
            String termo
    ) {
        var outputs = buscarItensCardapioPorNome.executar(termo);
        var body = outputs.stream().map(ItemCardapioApiMapper::toResponseFromOutput).toList();
        if (body.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(body);
    }



    @Override
    public ResponseEntity<ItemCardapioResponse> atualizarItemCardapio(UUID id,
                                                                      ItemCardapioRequest req) {

        var input = new AtualizarItemCardapioInput(
                id,
                req.getNome(),
                req.getDescricao(),
                req.getPreco(),
                req.getConsumoLocal(),
                req.getCaminhoFoto(),
                req.getRestauranteId()
        );

        ItemCardapioOutput item = atualizarUC.executar(input);
        return ResponseEntity.ok(ItemCardapioApiMapper.toResponse(item));
    }

    @Override
    public ResponseEntity<Void> removerItemCardapioPorId(UUID id) {
        removerUC.executar(id);
        return ResponseEntity.noContent().build();
    }
}
