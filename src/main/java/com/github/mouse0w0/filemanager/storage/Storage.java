package com.github.mouse0w0.filemanager.storage;

import com.github.mouse0w0.filemanager.storage.setting.StorageSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.mouse0w0.filemanager.util.PathUtils.createDirectoriesIfNotExists;
import static com.github.mouse0w0.filemanager.util.PathUtils.createFileIfNotExists;

public class Storage {

    public static final String SETTINGS_FILE = "Settings.json";

    private final Path path;
    private final Path dataPath;

    private StorageSettings settings;

    public Storage(Path path) {
        this.path = path;
        this.dataPath = path.resolve(".storage");
        createDirectoriesIfNotExists(this.dataPath);
        loadSettings();
    }

    public Path getPath() {
        return path;
    }

    public String getName() {
        return settings.getName();
    }

    public StorageSettings getSettings() {
        return settings;
    }

    public void loadSettings() {
        Path settingsFile = dataPath.resolve(SETTINGS_FILE);
        createFileIfNotExists(settingsFile);
        try (var reader = Files.newBufferedReader(settingsFile)) {
            settings = StorageSettings.GSON.fromJson(reader, StorageSettings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (settings == null) {
            settings = new StorageSettings();
        }
    }

    public void saveSettings() {
        Path settingsFile = dataPath.resolve(SETTINGS_FILE);
        createFileIfNotExists(settingsFile);
        try (var writer = Files.newBufferedWriter(settingsFile)) {
            StorageSettings.GSON.toJson(settings, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
