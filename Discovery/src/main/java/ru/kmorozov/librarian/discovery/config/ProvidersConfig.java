package ru.kmorozov.librarian.discovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kmorozov.librarian.discovery.ItemsProvider;
import ru.kmorozov.librarian.discovery.filesystem.FilesystemItemsProvider;

/**
 * Created by sbt-morozov-kv on 27.06.2016.
 */

@Configuration
public class ProvidersConfig {

    @Bean
    public ItemsProvider itemsProvider() {
        return new FilesystemItemsProvider("C:\\Users\\sbt-morozov-kv\\Desktop\\Документы");
    }
}
