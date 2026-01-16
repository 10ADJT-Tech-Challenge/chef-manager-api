
package com.adjt.chefmanagerapi.core.exceptions;

public class DescricaoObrigatoriaException extends RuntimeException {
    public DescricaoObrigatoriaException() {
        super("Descrição é obrigatória.");
    }
}