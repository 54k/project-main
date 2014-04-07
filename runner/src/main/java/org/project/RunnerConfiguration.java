package org.project;

import org.project.persistence.PersistenceConfiguration;
import org.project.web.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableAsync
@Import({PersistenceConfiguration.class, WebConfiguration.class})
public class RunnerConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(RunnerConfiguration.class, args);
    }
}
