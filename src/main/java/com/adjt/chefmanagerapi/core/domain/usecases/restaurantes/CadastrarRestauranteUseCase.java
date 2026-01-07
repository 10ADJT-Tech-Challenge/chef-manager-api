package com.adjt.chefmanagerapi.core.domain.usecases.restaurantes;

import com.adjt.chefmanagerapi.core.adapters.gateways.RestauranteGateway;
import com.adjt.chefmanagerapi.core.adapters.gateways.UsuarioGateway;
import com.adjt.chefmanagerapi.core.adapters.interfaces.restaurante.CadastrarRestaurante;
import com.adjt.chefmanagerapi.core.adapters.mappers.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.CadastrarRestauranteInput;
import com.adjt.chefmanagerapi.core.domain.dtos.restaurante.RestauranteOutput;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarRestauranteUseCase implements CadastrarRestaurante {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;
    private final RestauranteMapper mapper;

    public CadastrarRestauranteUseCase(
        RestauranteGateway restauranteGateway,
        RestauranteMapper mapper,
        UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RestauranteOutput executar(CadastrarRestauranteInput input) {
        Optional<Usuario> responsavel = usuarioGateway.buscarPorId(input.responsavel());

        Restaurante restaurante = restauranteGateway.salvar(
                new Restaurante(
                    input.nome(),
                    input.endereco(),
                    input.tipoCozinha(),
                    input.horarioFuncionamento(),
                    responsavel.orElse(null)
                )
        );

        return mapper.toOutput(restaurante);
    }
}
