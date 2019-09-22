package com.twl.salesfileprocessor.watcher;

import com.twl.salesfileprocessor.service.InputFileService;
import com.twl.salesfileprocessor.service.OutputFileService;
import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

            Path path = Paths.get(fileIn);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey watchKey;

            do {
                watchKey = watchService.take();
//                Path eventDir = keyMap.get(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    Path eventPath = (Path) event.context();

                    if (eventPath.toString().endsWith(".txt")) {

                        LOGGER.info("Iniciando a leitura e processamento do arquivo: " + eventPath);
                        Stream<String> lines = FileUtils.read(fileIn, eventPath.toString());
                        inputFileService.process(lines);
                        LOGGER.info("Finalizada a leitura e processamento do arquivo: " + eventPath);

                    } else {
                        LOGGER.info("##Extensão não válida");
                    }

                    outputFileService.createReport(eventPath.toString());
                }

            } while (watchKey.reset());
        } catch (Exception e) {
            // TODO
            LOGGER.error("Ocorreu um erro: " + e.getMessage());
        }
    }
}
