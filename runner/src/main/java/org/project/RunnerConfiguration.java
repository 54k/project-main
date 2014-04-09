package org.project;

import org.project.persistence.PersistenceConfiguration;
import org.project.web.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
@ComponentScan(basePackages = "org.project")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableAsync
@Import({PersistenceConfiguration.class, WebConfiguration.class})
public class RunnerConfiguration {

    @Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RunnerConfiguration.class);
        application.run(args);
    }
}
