package com.adjt.chefmanagerapi.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Endereco {
   private String rua;
    private String numero;
    private String cidade;
    private String cep;
    private String uf;

    public Endereco(String rua, String numero, String cidade, String cep, String uf) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
    }
}
