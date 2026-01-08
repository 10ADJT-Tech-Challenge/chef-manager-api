package com.adjt.chefmanagerapi.infrastructure.dataprovider.tipousuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tipo_usuario", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class TipoUsuarioEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoriaUsuario;
}
