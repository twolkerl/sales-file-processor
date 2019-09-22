package com.twl.salesfileprocessor.repository;

import com.twl.salesfileprocessor.model.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório para manipulação de {@link Venda}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface VendaRepository extends MongoRepository<Venda, Long> {
}
