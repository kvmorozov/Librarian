package ru.kmorozov.librarian.discovery;

import javax.swing.*;
import java.io.InputStream;
import java.util.List;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */
public interface Item {

    String getName();
    Item getParent();
    List<Item> getChildren();
    boolean hasChildren();

    Icon getIcon();
    InputStream getStream();
    String getMIMEType();

    boolean isLoaded();
    void setLoaded(boolean loadFlag);
}
