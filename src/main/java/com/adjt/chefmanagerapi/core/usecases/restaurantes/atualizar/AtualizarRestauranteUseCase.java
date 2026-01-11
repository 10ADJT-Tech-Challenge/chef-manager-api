package com.adjt.chefmanagerapi.core.usecases.restaurantes.atualizar;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AtualizarRestauranteUseCase implements AtualizarRestaurante {
    private final RestauranteGateway restauranteGateway;
    private final RestauranteMapper mapper;

    public AtualizarRestauranteUseCase(RestauranteGateway restauranteGateway, RestauranteMapper mapper) {
        this.restauranteGateway = restauranteGateway;
        this.mapper = mapper;
    }

    @Override
    public RestauranteOutput executar(AtualizarRestauranteInput dto) {
        Restaurante restaurante = restauranteGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new NoSuchElementException("Nenhum restaurante encontrado com o id: " + dto.id()));

        Optional.ofNullable(dto.nome()).ifPresent(restaurante::setNome);
        Optional.ofNullable(dto.endereco()).ifPresent(restaurante::setEndereco);
        Optional.ofNullable(dto.tipoCozinha()).ifPresent(restaurante::setTipoCozinha);
        Optional.ofNullable(dto.horarioFuncionamento()).ifPresent(restaurante::setHorarioFuncionamento);

        restauranteGateway.salvar(restaurante);
        return mapper.toOutput(restaurante);
    }
}
