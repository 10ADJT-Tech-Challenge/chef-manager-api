package com.adjt.chefmanagerapi.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.Usuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    BuscarUsuarioOutput toBuscarOutput(Usuario usuario);

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    CadastrarUsuarioOutput toCadastrarOutput(Usuario usuario);

    @Mapping(target = "tipo", expression = "java(usuario.getTipo().name())")
    AtualizarUsuarioOutput toAtualizarOutput(Usuario usuario);
}
