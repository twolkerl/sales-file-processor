package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.repository.ClienteRepository;
import com.twl.salesfileprocessor.repository.VendaRepository;
import com.twl.salesfileprocessor.repository.VendedorRepository;
import com.twl.salesfileprocessor.service.OutputFileService;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link OutputFileService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class OutputFileServiceImpl implements OutputFileService {

    private final VendedorRepository vendedorRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    public OutputFileServiceImpl(VendedorRepository vendedorRepository, ClienteRepository clienteRepository,
                                 VendaRepository vendaRepository) {

        this.vendedorRepository = vendedorRepository;
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
    }

    @Override
    public void createReport() {

    }
}
