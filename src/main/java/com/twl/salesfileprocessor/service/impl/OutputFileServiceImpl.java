package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.builder.ReportBuilder;
import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.repository.ClienteRepository;
import com.twl.salesfileprocessor.repository.VendedorRepository;
import com.twl.salesfileprocessor.service.OutputFileService;
import com.twl.salesfileprocessor.service.VendaService;
import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

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

        LOGGER.info("Buscando dados para geração do relatório...");

        Integer qtdClientes = clienteRepository.findAll().size();
        Integer qtdVendedores = vendedorRepository.findAll().size();
        Optional<Venda> vendaMaisCara = vendaService.findVendaMaisCara();
        Optional<Venda> vendaComPiorVendedor = vendaService.findVendaComPiorVendedor();

        if (noContentFound(qtdClientes, qtdVendedores, vendaMaisCara, vendaComPiorVendedor)) {
            LOGGER.info("Não foram encontrados dados.");
        } else {

            LOGGER.info("Dados encontrados! Iniciando a geração do relatório...");

            String report = ReportBuilder.getInstance()
                    .withHeader(fileName)
                    .withQuantidadeClientes(qtdClientes)
                    .withQuantidadeVendedores(qtdVendedores)
                    .withVendaMaisCara(vendaMaisCara)
                    .withPiorVendedor(vendaComPiorVendedor)
                    .build();

            LOGGER.info("Relatório concluído. Salvando o arquivo de saída...");

            writeFile(fileName, report);
        }
    }

    @Override
    public void wipeDatabase() {
        LOGGER.info("Limpando a base de dados para nova operação...");
        vendedorRepository.deleteAll();
        clienteRepository.deleteAll();
        vendaService.deleteAll();
        LOGGER.info("Base de dados limpa com sucesso!");
    }

    private void writeFile(String fileName, String report) {
        try {

            FileUtils.write(fileOut.concat("/"), fileName, report);
            LOGGER.info("Arquivo gerado com sucesso!");

            wipeDatabase();

        } catch (IOException e) {
            LOGGER.error("Ocorreu um erro durante a geração do relatório: ");
            LOGGER.error(e.getMessage());
        }
    }

    private boolean noContentFound(Integer qtdClientes, Integer qtdVendedores,
                                   Optional<Venda> vendaMaisCara, Optional<Venda> vendaComPiorVendedor) {
        return qtdClientes == 0 && qtdVendedores ==0 && !vendaMaisCara.isPresent() && !vendaComPiorVendedor.isPresent();
    }
}
