package com.twl.salesfileprocessor.enums;

import lombok.Getter;

import java.util.stream.Stream;

public enum TipoArquivoEnum {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    @Getter
    private String code;

    TipoArquivoEnum(String code) {
        this.code = code;
    }

    /**
     * Obtem o {@link TipoArquivoEnum} através do código informado.
     *
     * @param code Código de {@link TipoArquivoEnum}
     * @return {@link TipoArquivoEnum} correspondente
     */
    public static TipoArquivoEnum getValue(String code) {
        return Stream.of(values())
                .filter(e -> e.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
