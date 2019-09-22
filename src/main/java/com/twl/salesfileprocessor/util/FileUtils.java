package com.twl.salesfileprocessor.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Classe utilitária com operações de arquivos.
 */
public class FileUtils {

    /**
     * Método para ler o arquivo, retornando um {@link Stream} de {@link String} com cada linha do mesmo.
     *
     * @param path Caminho do arquivo. Ex.: data/in
     * @param fileName Nome do arquivo. Ex.: arquivo.txt
     * @return {@link Stream} de {@link String} contendo cada linha não vazia do arquivo
     * @throws IOException  Exception {@link IOException}
     */
    public static Stream<String> read(String path, String fileName) throws IOException {
        Path filePath = Paths.get(path.concat("/").concat(fileName));
        return Files.lines(filePath, StandardCharsets.UTF_8).filter(line -> !line.isEmpty());
    }

    /**
     * Método que valida se a linha informada é válida.
     *
     * @param line Linha do arquivo
     * @return True caso seja válida, caso contrário false
     */
    public static boolean isValidInputLine(String line) {

        // Linha não deve ser nula ou vazia
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        String[] fields = line.split("ç");

        // Deve conter 4 strings separadas por 'ç'
        if (fields.length != 4) {
            return false;
        }

        // Por fim, valida se a primeira String contém 3 dígitos.
        return fields[1].length() == 3;
    }
}
