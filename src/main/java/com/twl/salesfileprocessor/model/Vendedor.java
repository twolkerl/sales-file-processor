package com.twl.salesfileprocessor.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Document
@Data
public class Vendedor {

    @Id
    private String cpf;
    private String nome;
    private BigDecimal salario;
}
