package com.adjt.chefmanagerapi.core.domain.valueobjects;

import com.adjt.chefmanagerapi.core.exceptions.EnderecoInvalidoException;

import java.util.regex.Pattern;

public record Endereco(String rua, String numero, String cidade, String cep, String uf) {
    private static final String CEP_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern CEP_PATTERN = Pattern.compile(CEP_REGEX);

    private static final String UF_REGEX = "^[a-zA-Z]{2}$";
    private static final Pattern UF_PATTERN = Pattern.compile(UF_REGEX);

    public static final String MSG_RUA_OBRIGATORIA = "A rua é obrigatória";
    public static final String MSG_NUMERO_OBRIGATORIO = "O número é obrigatório";
    public static final String MSG_CIDADE_OBRIGATORIA = "A cidade é obrigatória";
    public static final String MSG_CEP_OBRIGATORIO = "O CEP é obrigatório";
    public static final String MSG_UF_OBRIGATORIA = "A UF é obrigatória";
    public static final String MSG_CEP_FORMATO_INVALIDO = "CEP inválido. Use o formato: 12345-678";
    public static final String MSG_UF_FORMATO_INVALIDO = "A UF deve ter exatamente 2 caracteres";


    public Endereco {
        validarCamposObrigatorios(rua, numero, cidade, cep, uf);
        validarCep(cep);
        validarUf(uf);

        uf = uf.toUpperCase();
        cep = normalizarCep(cep);
    }


    private static void validarCamposObrigatorios(String rua, String numero, String cidade, String cep, String uf) {
        if (rua == null || rua.trim().isEmpty())
            throw new EnderecoInvalidoException(MSG_RUA_OBRIGATORIA);

        if (numero == null || numero.trim().isEmpty())
            throw new EnderecoInvalidoException(MSG_NUMERO_OBRIGATORIO);

        if (cidade == null || cidade.trim().isEmpty())
            throw new EnderecoInvalidoException(MSG_CIDADE_OBRIGATORIA);

        if (cep == null || cep.trim().isEmpty())
            throw new EnderecoInvalidoException(MSG_CEP_OBRIGATORIO);

        if (uf == null || uf.trim().isEmpty())
            throw new EnderecoInvalidoException(MSG_UF_OBRIGATORIA);
    }

    private static void validarCep(String cep) {
        String cepLimpo = cep.replaceAll("\\s", "");
        if (!CEP_PATTERN.matcher(cepLimpo).matches())
            throw new EnderecoInvalidoException(MSG_CEP_FORMATO_INVALIDO);
    }

    private static void validarUf(String uf) {
        if (!UF_PATTERN.matcher(uf.trim()).matches())
            throw new EnderecoInvalidoException(MSG_UF_FORMATO_INVALIDO);
    }

    private static String normalizarCep(String cep) {
        String cepLimpo = cep.replaceAll("\\s", "");
        if (cepLimpo.length() == 8)
            return cepLimpo.substring(0, 5) + "-" + cepLimpo.substring(5);

        return cepLimpo;
    }
}
