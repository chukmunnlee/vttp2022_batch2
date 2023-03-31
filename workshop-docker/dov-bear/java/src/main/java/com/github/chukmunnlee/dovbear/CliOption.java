package com.github.chukmunnlee.dovbear;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

import static com.github.chukmunnlee.dovbear.Constants.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class CliOption {

    private String name = "";
    private String hash = "";
    private Integer port = 3000;

    public CliOption(ApplicationArguments args) {
        getValue(OPT_NAME, args).ifPresentOrElse(
            v -> this.name = v,
            () -> this.name = getEnv(ENV_INSTANCE_NAME) 
        );
        getValue(OPT_HASH, args).ifPresentOrElse(
            v -> this.hash = v,
            () -> this.hash = getEnv(ENV_INSTANCE_HASH)
        );
        getValue(OPT_PORT, args).ifPresentOrElse(
            v -> this.port = Integer.parseInt(v),
            () -> {
                String v = getEnv(ENV_PORT, "3000");
                this.port = Integer.parseInt(v);
            }
        );
    }

    public String getName() { return name; }
    public String getHash() { return hash; }
    public Integer getPort() { return port; }

    public static String getEnv(String envVar) {
        return CliOption.getEnv(envVar, "");
    }
    public static String getEnv(String envVar, String defVal) {
        String v = System.getenv(envVar);
        if (Objects.nonNull(v))
            return v;
        return defVal;
    }

    public static Optional<String> getValue(String opt, ApplicationArguments args) {
        List<String> values = args.getOptionValues(opt);
        if (Objects.nonNull(values) && (values.size() > 0)) 
            return Optional.of(values.get(0));
        return Optional.empty();
    }

    @Bean
    public ServletContextRequestLoggingFilter requestLog() {
        final ServletContextRequestLoggingFilter logger = new ServletContextRequestLoggingFilter();
        logger.setIncludeQueryString(true);
        return (logger);
    }
}
