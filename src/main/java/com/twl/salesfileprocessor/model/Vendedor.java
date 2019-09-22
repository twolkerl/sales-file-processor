package com.twl.salesfileprocessor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Vendedor {

    @Id
    private String cpf;
    private String nome;
    private BigDecimal salario;
}
