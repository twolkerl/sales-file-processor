package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Item;
import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.repository.VendaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class VendaServiceImplTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaServiceImpl service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new VendaServiceImpl(vendaRepository);
    }

    @Test
    public void findVendaMaisCara() {
        Venda venda = mockVendaWithIdAndPreco(1L, BigDecimal.TEN);
        Venda venda2 = mockVendaWithIdAndPreco(2L, BigDecimal.ONE);
        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);
        vendas.add(venda2);

        when(vendaRepository.findAll()).thenReturn(vendas);

        Optional<Venda> vendaMaisCara = service.findVendaMaisCara();

        assertEquals(vendaMaisCara.orElse(null), venda);
    }

    @Test
    public void findVendaComPiorVendedor() {
        Venda venda = mockVendaWithIdAndPreco(1L, BigDecimal.TEN);
        Venda venda2 = mockVendaWithIdAndPreco(2L, BigDecimal.ONE);
        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);
        vendas.add(venda2);

        when(vendaRepository.findAll()).thenReturn(vendas);

        Optional<Venda> vendaComPiorVendedor = service.findVendaComPiorVendedor();

        assertEquals(vendaComPiorVendedor.orElse(null), venda2);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();

        verify(vendaRepository, times(1)).deleteAll();
    }

    @Test
    public void save() {
        Venda venda = mockVendaWithIdAndPreco(null, BigDecimal.ONE);

        when(vendaRepository.save(venda)).thenReturn(mockVendaWithIdAndPreco(1L, BigDecimal.ONE));

        Venda savedVenda = service.save(venda);

        assertEquals(savedVenda, mockVendaWithIdAndPreco(1L, BigDecimal.ONE));
    }

    private Venda mockVendaWithIdAndPreco(Long id, BigDecimal preco) {
        return new Venda(id,
                Collections.singletonList(new Item(id, 1, preco)),
                "abc");
    }
}