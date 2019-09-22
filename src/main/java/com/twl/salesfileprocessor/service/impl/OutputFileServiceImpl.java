package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.builder.ReportBuilder;
import com.twl.salesfileprocessor.repository.ClienteRepository;
import com.twl.salesfileprocessor.repository.VendaRepository;
import com.twl.salesfileprocessor.repository.VendedorRepository;
import com.twl.salesfileprocessor.service.OutputFileService;
import com.twl.salesfileprocessor.service.VendaService;
import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Implementação de {@link OutputFileService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class OutputFileServiceImpl implements OutputFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputFileServiceImpl.class);

    @Value("${file.out}")
    private String fileOut;

    private final VendedorRepository vendedorRepository;
    private final ClienteRepository clienteRepository;
    private final VendaService vendaService;

    public OutputFileServiceImpl(VendedorRepository vendedorRepository, ClienteRepository clienteRepository,
                                 VendaService vendaService) {

        this.vendedorRepository = vendedorRepository;
        this.clienteRepository = clienteRepository;
        this.vendaService = vendaService;
    }

    @Override
    public void createReport(String fileName) {
        LOGGER.info("Iniciando a geração do relatório...");

        String report = ReportBuilder.getInstance()
                .withHeader(fileName)
                .withQuantidadeClientes(clienteRepository.count())
                .withQuantidadeVendedores(vendedorRepository.count())
                .withVendaMaisCara(vendaService.findVendaMaisCara())
                .withPiorVendedor(vendaService.findVendaWithPiorVendedor())
                .build();

        try {

            FileUtils.write(fileOut, fileName, report);
            LOGGER.info("Relatório gerada com sucesso!");

        } catch (IOException e) {
            LOGGER.error("Ocorreu um erro durante a geração do relatório: ");
            LOGGER.error(e.getMessage());
        }
    }
}
