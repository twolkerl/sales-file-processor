package com.twl.salesfileprocessor.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Getter
@Setter
@EqualsAndHashCode(exclude = "itens")
@ToString(exclude = "itens")
public class Venda {

    @Id
    private Long idVenda;
    private List<Item> itens;
    private String nomeVendedor;

}
