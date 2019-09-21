package com.twl.salesfileprocessor.enums;

import lombok.Getter;

public enum TipoArquivoEnum {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    @Getter
    private String code;

    TipoArquivoEnum(String code) {
        this.code = code;
    }
}
