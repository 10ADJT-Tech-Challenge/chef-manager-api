package com.adjt.chefmanagerapi.core.gateways.usuario;

import com.adjt.chefmanagerapi.core.gateways.tipousuario.TipoUsuarioGatewayDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UsuarioGatewayDTO(
    UUID id,
    String nome,
    String email,
    String login,
    String senha,
    TipoUsuarioGatewayDTO tipo,
    EnderecoGatewayDTO endereco,
    OffsetDateTime dataUltimaAlteracao
) {
    public record EnderecoGatewayDTO (
        String rua,
        String numero,
        String cidade,
        String cep,
        String uf
        ) {
    }
}
