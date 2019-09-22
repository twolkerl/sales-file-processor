package com.twl.salesfileprocessor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Classe que representa um Vendedor.
 *
 * @author tiago.wolker
 * @since 21/09/2019
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    private String cpf;
    private String nome;
    private BigDecimal salario;
}
