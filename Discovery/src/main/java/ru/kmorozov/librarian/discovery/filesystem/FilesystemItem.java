package ru.kmorozov.librarian.discovery.filesystem;

import ru.kmorozov.librarian.discovery.Item;
import sun.misc.Resource;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */
public class FilesystemItem implements Item {

    private String name;
    private FilesystemItem parent;
    private File file;

    public FilesystemItem(String name, FilesystemItem parent) {
        this.name = name;
        this.parent = parent;

        file = new File(name);
    }

    private FilesystemItem(File file, FilesystemItem parent) {
        this.file = file;
        this.name = file.getName();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Item getParent() {
        return parent;
    }

    public List<Item> getChildren() {
        if (file.isDirectory()) {
            List<Item> items = new ArrayList<>();
            for (File child : file.listFiles())
                items.add(new FilesystemItem(child, this));

            return items;
        } else
            return null;
    }

    @Override
    public boolean hasChildren() {
        return file.isDirectory();
    }

    @Override
    public Icon getIcon() {
        return FileSystemView.getFileSystemView().getSystemIcon(file);
    }

    @Override
    public String toString() {
        return name;
    }
}