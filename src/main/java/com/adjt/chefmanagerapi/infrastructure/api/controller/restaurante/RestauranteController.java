package com.adjt.chefmanagerapi.infrastructure.api.controller.restaurante;

import com.adjt.chefmanagerapi.RestauranteApi;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar.AtualizarRestaurante;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar.BuscarRestaurantePorId;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.buscar.BuscarRestaurantePorNome;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar.CadastrarRestaurante;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.deletar.DeletarRestaurantePorId;
import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteResponse;
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
    public ResponseEntity<RestauranteResponse> atualizarRestaurante(UUID id, AtualizarRestauranteRequest atualizarRestauranteRequest) {
        RestauranteOutput restaurante = atualizar.executar(mapper.toAtualizarInput(id, atualizarRestauranteRequest));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(restaurante));
    }

    @Override
    public ResponseEntity<RestauranteResponse> buscarRestaurantePorId(UUID id) {
        RestauranteOutput restaurante = buscarPorId.executar(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(restaurante));
    }

    @Override
    public ResponseEntity<List<RestauranteResponse>> buscarRestaurantePorNome(String nome) {
        List<RestauranteOutput> restaurantes = buscarPorNome.executar(nome);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes.stream().map(mapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<RestauranteResponse> criarRestaurante(RestauranteRequest restauranteRequest) {
        RestauranteOutput restaurante = cadastrar.executar(mapper.toCadastrarInput(restauranteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(restaurante));
    }

    @Override
    public ResponseEntity<Void> deletarRestaurantePorId(UUID id) {
        deletar.executar(id);
        return ResponseEntity.noContent().build();
    }
}
