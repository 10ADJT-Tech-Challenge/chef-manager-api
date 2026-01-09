package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.TipoUsuario;
import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.domain.factories.UsuarioFactory;
import com.adjt.chefmanagerapi.core.domain.valueobjects.CategoriaUsuario;
import com.adjt.chefmanagerapi.core.domain.valueobjects.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioGatewayMapper {

    @Mapping(target = "tipo.id", expression = "java(tipoUsuario.getId())")
    @Mapping(target = "tipo.nome", expression = "java(tipoUsuario.getNome())")
    @Mapping(target = "tipo.categoriaUsuario", expression = "java(tipoUsuario.getCategoriaUsuario().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    UsuarioGatewayDTO toGatewayDTO(Usuario usuario);

    default Usuario toDomain(UsuarioGatewayDTO usuarioGatewayDTO) {
        return UsuarioFactory.criarUsuario(
                usuarioGatewayDTO.id(),
                usuarioGatewayDTO.nome(),
                usuarioGatewayDTO.email(),
                usuarioGatewayDTO.login(),
                usuarioGatewayDTO.senha(),
                new TipoUsuario(usuarioGatewayDTO.tipo().id(),
                        usuarioGatewayDTO.tipo().nome(),
                        CategoriaUsuario.valueOf(usuarioGatewayDTO.tipo().categoriaUsuario())),
                new Endereco(usuarioGatewayDTO.endereco().rua(),
                        usuarioGatewayDTO.endereco().numero(),
                        usuarioGatewayDTO.endereco().cidade(),
                        usuarioGatewayDTO.endereco().cep(),
                        usuarioGatewayDTO.endereco().uf())
        );
    }
}
