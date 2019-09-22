package com.twl.salesfileprocessor.model;

import com.twl.salesfileprocessor.enums.TipoArquivoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private String cnpj;
    private String nome;
    private String areaNegocio;
}
