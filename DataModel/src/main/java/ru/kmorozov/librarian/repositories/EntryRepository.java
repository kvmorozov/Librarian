package ru.kmorozov.librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmorozov.librarian.entities.Entry;

import java.util.List;

/**
 * Created by sbt-morozov-kv on 23.06.2016.
 */

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByDescriptionStartsWithIgnoreCase(String description);
}
