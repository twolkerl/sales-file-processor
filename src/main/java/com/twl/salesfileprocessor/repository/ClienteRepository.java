package com.twl.salesfileprocessor.repository;

import com.twl.salesfileprocessor.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório para manipulação de {@link Cliente}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
