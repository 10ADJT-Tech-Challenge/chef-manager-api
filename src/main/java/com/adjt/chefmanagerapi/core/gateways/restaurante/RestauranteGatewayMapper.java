package com.adjt.chefmanagerapi.core.gateways.restaurante;

import com.adjt.chefmanagerapi.core.domain.entities.Restaurante;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteGatewayMapper {
    @Mapping(target = "responsavelId", expression = "java(restaurante.getResponsavel().getId())")
    @Mapping(target = "responsavel", ignore = true)
    RestauranteGatewayDto toGatewayDTO(Restaurante restaurante);

    default Restaurante toDomain(RestauranteGatewayDto restauranteGatewayDto) {
        UsuarioGatewayDTO responsavel = restauranteGatewayDto.responsavel();
        return new Restaurante(
                restauranteGatewayDto.id(),
                restauranteGatewayDto.nome(),
                restauranteGatewayDto.endereco(),
                restauranteGatewayDto.tipoCozinha(),
                restauranteGatewayDto.horarioFuncionamento(),
                UsuarioFactory.criarUsuario(
                        responsavel.id(),
                        responsavel.nome(),
                        responsavel.email(),
                        responsavel.login(),
                        responsavel.senha(),
                        responsavel.tipo(),
                        new Endereco(responsavel.endereco().rua(),
                                responsavel.endereco().numero(),
                                responsavel.endereco().cidade(),
                                responsavel.endereco().cep(),
                                responsavel.endereco().uf())
                )
        );
    }

}
