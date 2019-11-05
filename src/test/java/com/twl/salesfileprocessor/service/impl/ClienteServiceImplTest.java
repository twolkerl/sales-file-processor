package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Cliente;
import com.twl.salesfileprocessor.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    public void shouldSuccesSave() {

        Cliente cliente = new Cliente();

        cliente.setNome("qwe");
        cliente.setAreaNegocio("asd");
        cliente.setCnpj("123");

        service.save(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }
}