package com.application.teste.datacore;

import com.application.teste.datacore.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class DataCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCoreApplication.class, args);
    }

}
