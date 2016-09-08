package ru.kmorozov.librarian.discovery.filesystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sbt-morozov-kv on 06.09.2016.
 */
public class FileUtils {

    public static String getHash(File file) {
        try {
            try (FileInputStream fis = new FileInputStream(file)) {
                return org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
            }
        } catch (IOException e) {
            return null;
        }
    }
}
