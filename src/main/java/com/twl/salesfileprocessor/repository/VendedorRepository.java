package com.twl.salesfileprocessor.repository;

import com.twl.salesfileprocessor.model.Vendedor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório para manipulação de {@link Vendedor}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface VendedorRepository extends MongoRepository<Vendedor, String> {
}
