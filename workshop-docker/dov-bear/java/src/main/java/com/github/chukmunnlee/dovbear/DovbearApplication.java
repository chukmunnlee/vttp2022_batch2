package com.github.chukmunnlee.dovbear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import static com.github.chukmunnlee.dovbear.Constants.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
public class DovbearApplication implements ApplicationRunner, WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    private final Logger logger = Logger.getLogger(DovbearApplication.class.getName());

	@Autowired private CliOption cliOpts;

	public static void main(String[] args) {
		SpringApplication.run(DovbearApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
        logger.info("Application started on port %d at %s".formatted(cliOpts.getPort(), (new Date()).toString()));
	}

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(cliOpts.getPort());
    }
}
