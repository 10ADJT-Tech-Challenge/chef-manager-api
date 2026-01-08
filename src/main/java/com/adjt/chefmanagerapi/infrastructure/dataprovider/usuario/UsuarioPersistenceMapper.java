package com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioPersistenceMapper {

    @Mappings({
            @Mapping(source = "endereco.rua", target = "rua"),
            @Mapping(source = "endereco.numero", target = "numero"),
            @Mapping(source = "endereco.cidade", target = "cidade"),
            @Mapping(source = "endereco.cep", target = "cep"),
            @Mapping(source = "endereco.uf", target = "uf")
    })
    UsuarioEntity toEntity(UsuarioGatewayDTO usuario);

    @Mappings({
            @Mapping(source = "rua", target = "endereco.rua"),
            @Mapping(source = "numero", target = "endereco.numero"),
            @Mapping(source = "cidade", target = "endereco.cidade"),
            @Mapping(source = "cep", target = "endereco.cep"),
            @Mapping(source = "uf", target = "endereco.uf"),
            @Mapping(source = "senha", target = "senha")
    })
    UsuarioGatewayDTO toDto(UsuarioEntity entity);
}
