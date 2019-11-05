package com.twl.salesfileprocessor.service;

import com.twl.salesfileprocessor.model.Vendedor;

/**
 * Serviço com as funcionalidades de {@link com.twl.salesfileprocessor.model.Vendedor}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface VendedorService {

    /**
     * Salva um novo vendedor.
     *
     * @param vendedor {@link Vendedor} a ser salvo
     * @return {@link Vendedor}
     */
    Vendedor save(Vendedor vendedor);
}
