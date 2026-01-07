package com.adjt.chefmanagerapi.infrastructure.api.controller;

import com.adjt.chefmanagerapi.RestauranteApi;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.*;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.infrastructure.api.mapper.RestauranteApiMapper;
import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.Restaurante;
import com.adjt.chefmanagerapi.model.RestauranteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class RestauranteController implements RestauranteApi {
    private final CadastrarRestaurante cadastrar;
    private final AtualizarRestaurante atualizar;
    private final BuscarRestaurantePorId buscarPorId;
    private final BuscarRestaurantePorNome buscarPorNome;
    private final DeletarRestaurantePorId deletar;
    private final RestauranteApiMapper mapper;

    public RestauranteController(
            CadastrarRestaurante cadastrar,
            AtualizarRestaurante atualizar,
            BuscarRestaurantePorId buscar,
            BuscarRestaurantePorNome buscarPorNome,
            DeletarRestaurantePorId deletar,
            RestauranteApiMapper mapper) {
        this.cadastrar = cadastrar;
        this.atualizar = atualizar;
        this.buscarPorId = buscar;
        this.buscarPorNome = buscarPorNome;
        this.deletar = deletar;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Restaurante> atualizarRestaurante(UUID id, AtualizarRestauranteRequest atualizarRestauranteRequest) {
        RestauranteOutput restaurante = atualizar.executar(id, mapper.toInput(atualizarRestauranteRequest));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(restaurante));
    }

    @Override
    public ResponseEntity<Restaurante> buscarRestaurantePorId(UUID id) {
        RestauranteOutput restaurante = buscarPorId.executar(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(restaurante));
    }

    @Override
    public ResponseEntity<List<Restaurante>> buscarRestaurantePorNome(String nome) {
        List<RestauranteOutput> restaurantes = buscarPorNome.executar(nome);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes.stream().map(mapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<Restaurante> criarRestaurante(RestauranteRequest restauranteRequest) {
        var restaurante = cadastrar.executar(mapper.toInput(restauranteRequest));
        var response = mapper.toResponse(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> deletarRestaurantePorId(UUID id) {
        deletar.executar(id);
        return ResponseEntity.noContent().build();
    }
}
