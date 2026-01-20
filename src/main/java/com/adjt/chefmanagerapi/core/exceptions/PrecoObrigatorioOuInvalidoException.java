
package com.adjt.chefmanagerapi.core.exceptions;

public class PrecoObrigatorioOuInvalidoException extends RuntimeException {
    public PrecoObrigatorioOuInvalidoException() {
        super("Preço é obrigatório e deve ser maior que zero.");
    }
}
