package com.adjt.chefmanagerapi.core.usecases.restaurantes.cadastrar;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteMapper;
import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.usecases.restaurantes.RestauranteOutput;
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
        UsuarioGateway usuarioGateway
    ) {
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
