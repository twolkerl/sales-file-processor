package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.repository.VendaRepository;
import com.twl.salesfileprocessor.service.VendaService;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link VendaService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository repository;

    public VendaServiceImpl(VendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Venda venda) {
        repository.save(venda);
    }
}
