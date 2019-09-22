package com.twl.salesfileprocessor.service;

import java.util.stream.Stream;

/**
 * Serviço com as operações de entrada de arquivos.
 */
public interface InputFileService {

    /**
     * Processa as linhas do arquivo.
     *
     * @param fileLines {@link Stream} de {@link String} contendo cada linha do arquivo
     */
    void process(Stream<String> fileLines) throws IllegalArgumentException;
}
