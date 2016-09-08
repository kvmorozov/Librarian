package ru.kmorozov.librarian.discovery;

import ru.kmorozov.librarian.interfaces.Item;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */
public interface ItemsProvider {

    Item getRoot();
}
