package ru.kmorozov.librarian.interfaces;

import java.io.InputStream;

/**
 * Created by sbt-morozov-kv on 06.09.2016.
 */
public interface Document extends Item {

    InputStream getStream();

    String getMIMEType();
}
