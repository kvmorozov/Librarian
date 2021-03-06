package ru.kmorozov.librarian.interfaces;

import javax.swing.*;
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

    boolean isLoaded();
    void setLoaded(boolean loadFlag);

    String getKey();
}
