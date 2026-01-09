package com.adjt.chefmanagerapi.core.usecases.usuario;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuarioOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "tipo.id", expression = "java(tipoUsuario.getId())")
    @Mapping(target = "tipo.nome", expression = "java(tipoUsuario.getNome())")
    @Mapping(target = "tipo.categoriaUsuario", expression = "java(tipoUsuario.getCategoriaUsuario().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    BuscarUsuarioOutput toBuscarOutput(Usuario usuario);

    @Mapping(target = "tipo.id", expression = "java(tipoUsuario.getId())")
    @Mapping(target = "tipo.nome", expression = "java(tipoUsuario.getNome())")
    @Mapping(target = "tipo.categoriaUsuario", expression = "java(tipoUsuario.getCategoriaUsuario().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    CadastrarUsuarioOutput toCadastrarOutput(Usuario usuario);

    @Mapping(target = "tipo.id", expression = "java(tipoUsuario.getId())")
    @Mapping(target = "tipo.nome", expression = "java(tipoUsuario.getNome())")
    @Mapping(target = "tipo.categoriaUsuario", expression = "java(tipoUsuario.getCategoriaUsuario().name())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().toString())")
    AtualizarUsuarioOutput toAtualizarOutput(Usuario usuario);
}
