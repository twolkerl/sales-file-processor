package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.model.Item;
import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.repository.VendaRepository;
import com.twl.salesfileprocessor.service.VendaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Venda> findVendaMaisCara() {

        List<Venda> vendas = repository.findAll();

        vendas.forEach(venda -> venda.setTotalVenda(venda.getItens()
                .stream()
                .map(Item::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add))
        );

        return vendas.stream()
                .max(Comparator.comparing(Venda::getTotalVenda));
    }

    @Override
    public Optional<Venda> findVendaComPiorVendedor() {

        List<Venda> vendas = repository.findAll();

        vendas.forEach(venda -> venda.setTotalVenda(venda.getItens()
                .stream()
                .map(Item::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add))
        );

        return vendas.stream()
                .min(Comparator.comparing(Venda::getTotalVenda));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
