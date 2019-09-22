package com.twl.salesfileprocessor;

import com.twl.salesfileprocessor.watcher.WatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesFileProcessorApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesFileProcessorApplication.class);

	@Autowired
	private WatcherService watcherService;

	public static void main(String[] args) {
		SpringApplication.run(SalesFileProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("Inicializando o WatcherService");
		watcherService.watch();
	}
}
