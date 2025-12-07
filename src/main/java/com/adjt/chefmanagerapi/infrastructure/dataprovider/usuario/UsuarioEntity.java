package com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsuarioEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(nullable = false, length = 32)
    private String tipo;

    private String rua;
    private String numero;
    private String cidade;
    private String cep;
    private String uf;

    @Column(nullable = false)
    private OffsetDateTime dataUltimaAlteracao;
}


