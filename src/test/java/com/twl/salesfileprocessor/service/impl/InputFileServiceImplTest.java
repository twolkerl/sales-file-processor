package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.service.ClienteService;
import com.twl.salesfileprocessor.service.VendaService;
import com.twl.salesfileprocessor.service.VendedorService;
import com.twl.salesfileprocessor.util.ConverterUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class InputFileServiceImplTest {

    @Mock
    private VendaService vendaService;

    @Mock
    private VendedorService vendedorService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private InputFileServiceImpl service;

    @Before
    public void setUp() {
        initMocks(this);
        service = new InputFileServiceImpl(vendedorService, clienteService, vendaService);
    }

    @Test
    public void process() {
        Stream<String> lines = Stream.of(createLineVenda(), createLineCliente(), createLineVendedor(), createInvalidLine());

        service.process(lines);

        verify(clienteService, times(1)).save(ConverterUtils.convertToCliente(splitFields(createLineCliente())));
        verify(vendaService, times(1)).save(ConverterUtils.convertToVenda(splitFields(createLineVenda())));
        verify(vendedorService, times(1)).save(ConverterUtils.convertToVendedor(splitFields(createLineVendedor())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failProcess() {
        Stream<String> lines = Stream.of(createErrorLine());

        service.process(lines);
    }

    private String[] splitFields(String line) {
        return line.split("ç");
    }

    private String createLineCliente() {
        return "002ç2345675434544345çJose da SilvaçRural";
    }

    private String createLineVenda() {
        return "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
    }

    private String createLineVendedor() {
        return "001ç1234567891234çPedroç50000";
    }

    private String createInvalidLine() {
        return "qweç";
    }

    private String createErrorLine() {
        return "zzzçzzzçzzzçzzz";
    }
}