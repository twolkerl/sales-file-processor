package com.twl.salesfileprocessor.service.impl;

import com.twl.salesfileprocessor.enums.TipoArquivoEnum;
import com.twl.salesfileprocessor.model.Cliente;
import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.model.Vendedor;
import com.twl.salesfileprocessor.service.ClienteService;
import com.twl.salesfileprocessor.service.InputFileService;
import com.twl.salesfileprocessor.service.VendaService;
import com.twl.salesfileprocessor.service.VendedorService;
import com.twl.salesfileprocessor.util.ConverterUtils;
import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Stream;

/**
 * Implementação de {@link com.twl.salesfileprocessor.service.InputFileService}.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
@Service
public class InputFileServiceImpl implements InputFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputFileServiceImpl.class);

    private final VendedorService vendedorService;
    private final ClienteService clienteService;
    private final VendaService vendaService;

    public InputFileServiceImpl(VendedorService vendedorService, ClienteService clienteService,
                                VendaService vendaService) {

        this.vendedorService = vendedorService;
        this.clienteService = clienteService;
        this.vendaService = vendaService;
    }

    @Override
    public void process(Stream<String> fileLines) throws IllegalArgumentException {

        fileLines.forEach(line -> {

            if (FileUtils.isValidInputLine(line)) {

                LOGGER.info("##Linha valida");
                String[] fields = line.split("ç");
                TipoArquivoEnum tipoArquivo = TipoArquivoEnum.getValue(fields[0]);

                switch (tipoArquivo) {
                    case VENDEDOR:
                        vendedorService.save(ConverterUtils.convertToVendedor(fields));
                        break;
                    case CLIENTE:
                        clienteService.save(ConverterUtils.convertToCliente(fields));
                        break;
                    case VENDA:
                        vendaService.save(ConverterUtils.convertToVenda(fields));
                        break;
                    default:
                        // TODO
                        LOGGER.error("Tipo de registro inválido!");
                }

            } else {
                LOGGER.error("Linha do arquivo inválida!");
            }
        });
    }
}
