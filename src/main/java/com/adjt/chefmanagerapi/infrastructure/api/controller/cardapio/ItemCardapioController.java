package com.adjt.chefmanagerapi.infrastructure.api.controller.cardapio;

import com.adjt.chefmanagerapi.ItemCardapioApi;
import com.adjt.chefmanagerapi.core.domain.entities.cardapio.ItemCardapio;
import com.adjt.chefmanagerapi.core.usecases.cardapio.ItemCardapioOutput;
import com.adjt.chefmanagerapi.core.usecases.cardapio.atualizar.AtualizarItemCardapioUseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.buscar.BuscarItemCardapioPorIdUseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.buscar.ListarItensCardapioPorIdRestauranteUseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.cadastrar.CadastrarItemCardapioUseCase;
import com.adjt.chefmanagerapi.core.usecases.cardapio.deletar.RemoverItemCardapioUseCase;
import com.adjt.chefmanagerapi.model.ItemCardapioRequest;
import com.adjt.chefmanagerapi.model.ItemCardapioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ItemCardapioController implements ItemCardapioApi {

    private final CadastrarItemCardapioUseCase criarUC;
    private final AtualizarItemCardapioUseCase atualizarUC;
    private final RemoverItemCardapioUseCase removerUC;
    private final BuscarItemCardapioPorIdUseCase buscarUC;
    private final ListarItensCardapioPorIdRestauranteUseCase listarPorRestauranteIdUC; // UC que vamos criar

    public ItemCardapioController(CadastrarItemCardapioUseCase criarUC,
                                  AtualizarItemCardapioUseCase atualizarUC,
                                  RemoverItemCardapioUseCase removerUC,
                                  BuscarItemCardapioPorIdUseCase buscarUC, ListarItensCardapioPorIdRestauranteUseCase listarPorRestauranteIdUC) {
        this.criarUC = criarUC;
        this.atualizarUC = atualizarUC;
        this.removerUC = removerUC;
        this.buscarUC = buscarUC;
        this.listarPorRestauranteIdUC = listarPorRestauranteIdUC;
    }

    @Override
    public ResponseEntity<ItemCardapioResponse> criarItemCardapio(@PathVariable UUID id, @RequestBody ItemCardapioRequest itemCardapioRequest) {
        ItemCardapio item = criarUC.executar(itemCardapioRequest.getNome(), itemCardapioRequest.getDescricao(), itemCardapioRequest.getPreco(),
                itemCardapioRequest.getConsumoLocal(), itemCardapioRequest.getCaminhoFoto(), id);
        return ResponseEntity.ok(ItemCardapioApiMapper.toResponse(item));
    }

    @Override
    public ResponseEntity<ItemCardapioResponse> buscarItemCardapioPorId(@PathVariable UUID id) {
        ItemCardapio item = buscarUC.executar(id);
        return ResponseEntity.ok(ItemCardapioApiMapper.toResponse(item));
    }


    @Override
    public ResponseEntity<List<ItemCardapioResponse>> buscarItensCardapioPorRestauranteId(UUID idRestaurante) {
        List<ItemCardapioOutput> outputs = listarPorRestauranteIdUC.executar(idRestaurante);
        List<ItemCardapioResponse> body = outputs.stream().map(ItemCardapioApiMapper::toResponseFromOutput).toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ItemCardapioResponse> atualizarItemCardapio(@PathVariable UUID id,
                                                                      @RequestBody ItemCardapioRequest req) {
        ItemCardapio item = atualizarUC.executar(id, req.getNome(), req.getDescricao(), req.getPreco(),
                req.getConsumoLocal(), req.getCaminhoFoto(), req.getIdRestaurante());
        return ResponseEntity.ok(ItemCardapioApiMapper.toResponse(item));
    }

    @Override
    public ResponseEntity<Void> removerItemCardapioPorId(@PathVariable UUID id) {
        removerUC.executar(id);
        return ResponseEntity.noContent().build();
    }
}
