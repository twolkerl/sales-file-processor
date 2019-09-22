package com.twl.salesfileprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    private Long id;
    private Integer quantidade;
    private BigDecimal preco;
}
