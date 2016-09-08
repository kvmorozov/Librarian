package ru.kmorozov.librarian.interfaces;

/**
 * Created by sbt-morozov-kv on 08.09.2016.
 */
public interface Book extends Document {

    BookMetadata getMetadata();
}
