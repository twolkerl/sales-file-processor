package com.twl.salesfileprocessor.watcher;

import com.twl.salesfileprocessor.service.InputFileService;
import com.twl.salesfileprocessor.service.OutputFileService;
import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Serviço observador dos diretórios de entrada e saída.
 *
 * @author tiago.wolker
 * @since 21/09/2019
 */
@Component
public class WatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherService.class);

    private final InputFileService inputFileService;
    private final OutputFileService outputFileService;

    public WatcherService(InputFileService inputFileService, OutputFileService outputFileService) {
        this.inputFileService = inputFileService;
        this.outputFileService = outputFileService;
    }

    @Value("${file.in}")
    private String fileIn;
    @Value("${file.out}")
    private String fileOut;

    public void watch() throws Exception {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            registerEvent(watchService);
            WatchKey watchKey;

            do {
                watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    Path eventPath = (Path) event.context();

                    if (eventPath.toString().endsWith(".txt")) {

                        LOGGER.info("Iniciando a leitura e processamento do arquivo: " + eventPath);

                        try {

                            Stream<String> lines = FileUtils.read(fileIn, eventPath.toString());
                            inputFileService.process(lines);
                            LOGGER.info("Finalizada a leitura e processamento do arquivo: " + eventPath);

                        } catch (IllegalArgumentException e) {

                            LOGGER.error("Ocorreu um erro ao tentar processar o arquivo. " +
                                    "Existem linhas fora do padrão de entrada!");
                            LOGGER.error("Erro: " + e.getMessage());

                            outputFileService.wipeDatabase();
                        } catch (Exception e) {

                            LOGGER.error("Ocorreu um erro desconhecido ao tentar processar o arquivo. " +
                                    "Por favor verifique o mesmo e tente novamente mais tarde.");

                            outputFileService.wipeDatabase();
                        }

                        outputFileService.createReport(eventPath.toString());

                    } else {
                        LOGGER.info("Extensão de arquivo não válida! O arquivo deve ser no formato '.txt'");
                    }
                }
            } while (watchKey.reset());

        } catch (IOException e) {
            LOGGER.error("Ocorreu um na inicialização do programa: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    private void registerEvent(WatchService watchService) throws IOException {
        Path path = Paths.get(fileIn);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }
}
