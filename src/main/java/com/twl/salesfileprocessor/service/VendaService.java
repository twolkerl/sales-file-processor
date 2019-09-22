package com.twl.salesfileprocessor.service;

import com.twl.salesfileprocessor.model.Venda;

/**
 * Serviço com as funcionalidades de {@link com.twl.salesfileprocessor.model.Venda}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface VendaService {

    /**
     * Salva uma nova venda.
     *
     * @param venda {@link Venda} a ser salva
     */
    void save(Venda venda);
}
