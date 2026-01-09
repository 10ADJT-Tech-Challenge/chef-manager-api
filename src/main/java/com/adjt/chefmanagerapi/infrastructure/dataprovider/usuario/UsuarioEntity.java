package com.adjt.chefmanagerapi.infrastructure.dataprovider.usuario;

import com.adjt.chefmanagerapi.infrastructure.dataprovider.tipousuario.TipoUsuarioEntity;
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

    @Column(name = "tipo_usuario_id", insertable = false, updatable = false)
    private UUID tipoUsuarioId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuarioEntity tipo;

    private String rua;
    private String numero;
    private String cidade;
    private String cep;
    private String uf;

    @Column
    private OffsetDateTime dataUltimaAlteracao;
}


