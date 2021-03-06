package com.github.mouse0w0.filemanager.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {

    public static void createDirectoriesIfNotExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createFileIfNotExists(Path path) {
        createDirectoriesIfNotExists(path.getParent());
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Path relativize(Path basePath, String path) {
        return basePath.relativize(Path.of(path));
    }
}
