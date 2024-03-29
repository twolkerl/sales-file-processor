package com.twl.salesfileprocessor.builder;

import com.twl.salesfileprocessor.model.Venda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por montar o relatório para o arquivo de saída.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public class ReportBuilder {

    StringBuilder builder = new StringBuilder();

    public static ReportBuilder getInstance() {
        return new ReportBuilder();
    }

    /**
     * Monta o cabeçalho do relatório com o nome do arquivo.
     *
     * @param fileName nome do arquivo de entrada
     * @return {@link ReportBuilder}
     */
    public ReportBuilder withHeader(String fileName) {

        builder.append("### Relatório do arquivo");

        if (fileName != null && !fileName.trim().isEmpty()) {
            builder.append(fileName);
        }
        builder.append(" ###")
                .append(System.getProperty("line.separator"));

        return this;
    }

    /**
     * Monta a quantidade de clientes no relatório.
     *
     * @param quantidadeClientes Quantidade de clientes
     * @return {@link ReportBuilder}
     */
    public ReportBuilder withQuantidadeClientes(Integer quantidadeClientes) {

        if (quantidadeClientes > 0) {
            builder.append("Quantidade de clientes no arquivo de entrada: ")
                    .append(quantidadeClientes)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    /**
     * Monta a quantidade de vendedores no relatório.
     *
     * @param quantidadeVendedores Quantidade de vendedores
     * @return {@link ReportBuilder}
     */
    public ReportBuilder withQuantidadeVendedores(Integer quantidadeVendedores) {

        if (quantidadeVendedores > 0) {
            builder.append("Quantidade de vendedores no arquivo de entrada: ")
                    .append(quantidadeVendedores)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    /**
     * Monta a venda mais cara no relatório.
     *
     * @param venda Venda mais cara.
     * @return {@link ReportBuilder}
     */
    public ReportBuilder withVendaMaisCara(Optional<Venda> venda) {

        venda.ifPresent(value -> builder.append("ID da venda mais cara: ")
                .append(value.getIdVenda())
                .append(System.getProperty("line.separator")));
        return this;
    }

    /**
     * Monta o pior vendedor no relatório.
     *
     * @param venda Venda com o pior vendedor
     * @return {@link ReportBuilder}
     */
    public ReportBuilder withPiorVendedor(Optional<Venda> venda) {
        venda.ifPresent(v -> builder.append("O pior vendedor: ")
                .append(v.getNomeVendedor()));

        return this;
    }

    public String build() {
        return builder.toString();
    }
}
