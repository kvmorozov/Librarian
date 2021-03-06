package ru.kmorozov.librarian.discovery.filesystem;

import ru.kmorozov.librarian.discovery.ItemsProvider;
import ru.kmorozov.librarian.interfaces.Item;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */
public class FilesystemItemsProvider implements ItemsProvider {

    private FilesystemItem root;

    public FilesystemItemsProvider(String rootPath) {
        root = new FilesystemItem(rootPath, null);
    }

    public Item getRoot() {
        return root;
    }
}
