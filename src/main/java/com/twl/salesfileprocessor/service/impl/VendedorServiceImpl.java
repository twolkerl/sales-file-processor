package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Vendedor;
import com.twl.salesfileprocessor.repository.VendedorRepository;
import com.twl.salesfileprocessor.service.VendedorService;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link VendedorService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Vendedor vendedor) {
        repository.save(vendedor);
    }
}
