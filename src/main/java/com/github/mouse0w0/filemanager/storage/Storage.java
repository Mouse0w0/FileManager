package com.github.mouse0w0.filemanager.storage;

import com.github.mouse0w0.filemanager.storage.setting.StorageSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.mouse0w0.filemanager.util.PathUtils.createDirectoriesIfNotExists;
import static com.github.mouse0w0.filemanager.util.PathUtils.createFileIfNotExists;

public class Storage {

    private final Path path;
    private final Path settingsPath;

    private StorageSettings settings;

    public Storage(Path path) {
        this.path = path;
        createDirectoriesIfNotExists(path);
        this.settingsPath = path.resolve("storage.settings.json");
        loadSettings();
    }

    public Path getPath() {
        return path;
    }

    public StorageSettings getSettings() {
        return settings;
    }

    public void loadSettings() {
        createFileIfNotExists(settingsPath);
        try (var reader = Files.newBufferedReader(settingsPath)) {
            settings = StorageSettings.GSON.fromJson(reader, StorageSettings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (settings == null) {
            settings = new StorageSettings();
        }
    }

    public void saveSettings() {
        createFileIfNotExists(settingsPath);
        try (var writer = Files.newBufferedWriter(settingsPath)) {
            StorageSettings.GSON.toJson(settings, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
