package com.adjt.chefmanagerapi.core.gateways.interfaces;

import com.adjt.chefmanagerapi.core.domain.entities.usuario.Usuario;

public interface TokenGeneratorGateway {
    String gerar(Usuario usuario);
}
