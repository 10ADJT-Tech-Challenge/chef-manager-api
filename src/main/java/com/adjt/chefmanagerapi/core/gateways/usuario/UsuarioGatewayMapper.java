package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioGatewayMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    UsuarioGatewayDTO toGatewayDTO(Usuario usuario);

    default Usuario toDomain(UsuarioGatewayDTO usuarioGatewayDTO) {
        return UsuarioFactory.criarUsuario(
                usuarioGatewayDTO.nome(),
                usuarioGatewayDTO.email(),
                usuarioGatewayDTO.login(),
                usuarioGatewayDTO.senha(),
                usuarioGatewayDTO.tipo(),
                usuarioGatewayDTO.endereco().rua(),
                usuarioGatewayDTO.endereco().numero(),
                usuarioGatewayDTO.endereco().cidade(),
                usuarioGatewayDTO.endereco().cep(),
                usuarioGatewayDTO.endereco().uf()
        );
    }
}
