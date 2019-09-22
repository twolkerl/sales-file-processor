package com.twl.salesfileprocessor.service;

import com.twl.salesfileprocessor.model.Venda;

import java.util.Optional;

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

    /**
     * Busca a venda mais cara.
     *
     * @return {@link Venda} mais cara
     */
    Optional<Venda> findVendaMaisCara();

    /**
     * Busca a venda com o pior vendedor.
     *
     * @return {Wlink Venda} com o pior vendedor
     */
    Optional<Venda> findVendaWithPiorVendedor();
}
