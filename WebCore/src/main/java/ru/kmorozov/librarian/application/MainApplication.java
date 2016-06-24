package ru.kmorozov.librarian.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kmorozov.librarian.entities.Entry;
import ru.kmorozov.librarian.repositories.EntryRepository;

import java.util.Arrays;

/**
 * Created by sbt-morozov-kv on 22.06.2016.
 */

@SpringBootApplication(scanBasePackages = "ru.kmorozov.librarian.controllers," +
                                          "ru.kmorozov.librarian.persistence.config")
@EnableJpaRepositories(basePackageClasses = EntryRepository.class)
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MainApplication.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    public CommandLineRunner loadData(EntryRepository repository) {
        return (args) -> {
            // save a couple of entries
            repository.save(new Entry("Jack", "Bauer"));
            repository.save(new Entry("Chloe", "O'Brian"));
            repository.save(new Entry("Kim", "Bauer"));
            repository.save(new Entry("David", "Palmer"));
            repository.save(new Entry("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Entry customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Entry customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Entry bauer : repository.findByDescriptionStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}