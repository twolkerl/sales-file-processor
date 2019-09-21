package com.twl.salesfileprocessor.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Document
@Data
public class Item {

    @Id
    private Long id;
    private Integer quantidade;
    private BigDecimal preco;
}
