package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Vendedor;
import com.twl.salesfileprocessor.repository.VendedorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class VendedorServiceImplTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorServiceImpl service;

    @Before
    public void setUp() {
        initMocks(this);
        service = new VendedorServiceImpl(vendedorRepository);
    }

    @Test
    public void save() {
        Vendedor vendedor = new Vendedor("99999999999", "Teste", BigDecimal.TEN);

        when(vendedorRepository.save(vendedor)).thenReturn(vendedor);

        Vendedor savedVendedor = service.save(vendedor);

        assertEquals(vendedor, savedVendedor);
    }
}