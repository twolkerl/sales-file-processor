package com.twl.salesfileprocessor.builder;

import com.twl.salesfileprocessor.model.Venda;

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

    public ReportBuilder withAmountClients(Long amount) {
        if (amount > 0) {
            builder.append("Quantidade de clientes no arquivo de entrada: ")
                    .append(amount)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    public ReportBuilder withAmountSalesMan(Long amount) {
        if (amount > 0) {
            builder.append("Quantidade de vendedor no arquivo de entrada: ")
                    .append(amount)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    public ReportBuilder withSalesMoreExpensive(Optional<Venda> venda) {
        venda.ifPresent(value -> builder.append("ID da venda mais cara: ")
                .append(value.getIdVenda())
                .append(System.getProperty("line.separator")));
        return this;
    }

    public ReportBuilder withWorstSeller(Optional<List<Venda>> sales) {
        sales.ifPresent(saleList -> builder.append("O pior vendedor: ")
                .append(saleList.iterator().next().getNomeVendedor()));

        return this;
    }

    public String build() {
        return builder.toString();
    }
}
