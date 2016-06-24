package ru.kmorozov.librarian.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sbt-morozov-kv on 23.06.2016.
 */

@Entity
public class Entry {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;


    protected Entry() {
    }

    public Entry(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Entry[id=%d, name='%s', description='%s']", id,
                name, description);
    }
}
