package com.adjt.chefmanagerapi.infrastructure.api.controller.auth;

import com.adjt.chefmanagerapi.core.usecases.auth.LoginUseCaseOutput;
import com.adjt.chefmanagerapi.model.AuthResponse;
import com.adjt.chefmanagerapi.model.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthApiMapper {

    @Mapping(target = "login", source = "login")
    @Mapping(target = "senha", source = "senha")
    com.adjt.chefmanagerapi.core.usecases.auth.LoginRequest toUseCaseInput(LoginRequest request);
    com.adjt.chefmanagerapi.core.usecases.auth.LoginRequest toInput(LoginRequest request);

    AuthResponse toResponse(LoginUseCaseOutput output);
}