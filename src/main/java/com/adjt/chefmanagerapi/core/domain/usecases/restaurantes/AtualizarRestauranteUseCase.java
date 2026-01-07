package com.adjt.chefmanagerapi.core.domain.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.AtualizarRestaurante;
import com.adjt.chefmanagerapi.core.adapters.mappers.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.AtualizarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtualizarRestauranteUseCase implements AtualizarRestaurante {
    private final RestauranteGateway restauranteGateway;
    private final RestauranteMapper mapper;

    public AtualizarRestauranteUseCase(RestauranteGateway restauranteGateway, RestauranteMapper mapper) {
        this.restauranteGateway = restauranteGateway;
        this.mapper = mapper;
    }

    @Override
    public RestauranteOutput executar(UUID id, AtualizarRestauranteInput dto) {
        Restaurante restaurante = restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum restaurante encontrado com o id: " + id));

        Optional.ofNullable(dto.nome()).ifPresent(restaurante::setNome);
        Optional.ofNullable(dto.endereco()).ifPresent(restaurante::setEndereco);
        Optional.ofNullable(dto.tipoCozinha()).ifPresent(restaurante::setTipoCozinha);
        Optional.ofNullable(dto.horarioFuncionamento()).ifPresent(restaurante::setHorarioFuncionamento);

        restauranteGateway.salvar(restaurante);
        return mapper.toOutput(restaurante);
    }
}
