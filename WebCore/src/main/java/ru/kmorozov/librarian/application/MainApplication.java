package ru.kmorozov.librarian.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kmorozov.librarian.entities.Entry;
import ru.kmorozov.librarian.repositories.EntryRepository;

/**
 * Created by sbt-morozov-kv on 22.06.2016.
 */

@SpringBootApplication(scanBasePackages = "ru.kmorozov.librarian.controllers," +
        "ru.kmorozov.librarian.persistence.config," +
        "ru.kmorozov.librarian.discovery.config")
@EnableJpaRepositories(basePackageClasses = EntryRepository.class)
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
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

            // fetch all entries
            log.info("Entries found with findAll():");
            log.info("-------------------------------");
            for (Entry entry : repository.findAll()) {
                log.info(entry.toString());
            }
            log.info("");

            // fetch an individual entry by ID
            Entry entry = repository.findOne(1L);
            log.info("Entry found with findOne(1L):");
            log.info("--------------------------------");
            log.info(entry.toString());
            log.info("");

            // fetch entries by last name
            log.info("Entry found with findByDescriptionStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Entry bauer : repository.findByDescriptionStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}