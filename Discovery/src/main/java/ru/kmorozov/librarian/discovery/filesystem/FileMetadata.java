package ru.kmorozov.librarian.discovery.filesystem;

import ru.kmorozov.librarian.discovery.filesystem.utils.FileUtils;
import ru.kmorozov.librarian.interfaces.DocumentMetadata;

import java.io.File;

/**
 * Created by sbt-morozov-kv on 08.09.2016.
 */
public class FileMetadata implements DocumentMetadata {

    private String MD5Hash;
    private String fileName;

    public FileMetadata(File file) {
        MD5Hash = FileUtils.getHash(file);
        fileName = file.getName();
    }

    @Override
    public String getKey() {
        return MD5Hash;
    }

    @Override
    public String getName() {
        return fileName;
    }
}
