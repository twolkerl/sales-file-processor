package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Vendedor;
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
    @Override
    public void save(Vendedor vendedor) {
        //TODO criar repository e chamar método save
    }
}
