package com.twl.salesfileprocessor.model;

import com.twl.salesfileprocessor.enums.TipoArquivoEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Cliente {

    @Id
    private String cnpj;
    private String nome;
    private String areaNegocio;
}
