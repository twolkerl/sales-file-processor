package com.twl.salesfileprocessor.service;

import com.twl.salesfileprocessor.model.Cliente;

/**
 * Serviço com as funcionalidades de {@link com.twl.salesfileprocessor.model.Cliente}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface ClienteService {

    /**
     * Salva um novo cliente.
     *
     * @param cliente {@link Cliente} a ser salvo
     */
    void save(Cliente cliente);
}
