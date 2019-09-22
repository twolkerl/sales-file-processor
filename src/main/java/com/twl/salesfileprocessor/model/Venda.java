package com.twl.salesfileprocessor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@EqualsAndHashCode(exclude = "itens")
@ToString(exclude = "itens")
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    private Long idVenda;
    private List<Item> itens;
    private String nomeVendedor;

}
