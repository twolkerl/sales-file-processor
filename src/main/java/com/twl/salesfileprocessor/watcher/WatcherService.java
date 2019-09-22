package com.twl.salesfileprocessor.watcher;

import com.twl.salesfileprocessor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class WatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherService.class);

    @Value("${file.in}")
    private String fileIn;
    @Value("${file.out}")
    private String fileOut;

    public void watch() throws Exception {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get(fileIn);
            keyMap.put(path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE),
                    path);

            WatchKey watchKey;

            do {
                watchKey = watchService.take();
                Path eventDir = keyMap.get(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()) {
//                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();

                    // TODO Fazer o método de processamento a partir daqui
                    if (eventPath.toString().endsWith(".txt")) {

                        LOGGER.info("Iniciando a leitura do arquivo: " + eventPath);
                        Stream<String> lines = FileUtils.read(fileIn, eventPath.toString());
                        LOGGER.info("Finalizada a leitura do arquivo: " + eventPath);

                    } else {
                        LOGGER.info("##Extensão não válida");
                    }
                }

            } while (watchKey.reset());
        } catch (Exception e) {
            // TODO
            LOGGER.error("Ocorreu um erro: " + e.getMessage());
        }
    }
}
