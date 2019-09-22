package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Cliente;
import com.twl.salesfileprocessor.repository.ClienteRepository;
import com.twl.salesfileprocessor.service.ClienteService;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link ClienteService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Cliente cliente) {
        repository.save(cliente);
    }
}
