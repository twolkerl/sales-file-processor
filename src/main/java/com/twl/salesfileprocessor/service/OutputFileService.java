package com.twl.salesfileprocessor.service;

/**
 * Serviço com as operações de saída de arquivos.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public interface OutputFileService {

    /**
     * Cria o relatório na pasta de saída conforme os dados obtidos no arquivo de entrada.
     * Após o término da criação do relatório a base é redefinida.
     *
     * @param fileName Nome do arquivo
     */
    void createReport(String fileName);

    /**
     * Método que limpa todas as coleções para que seja feito um novo relatório do próximo arquivo a ser processado.
     */
    void wipeDatabase();
}
